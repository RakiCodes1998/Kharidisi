import { useEffect, useState } from "react";
import "./App.css";

import Login from "./pages/Login";
import Register from "./pages/Register";
function App() {

    const [isLoggedIn, setIsLoggedIn] = useState(
        !!localStorage.getItem("token"));
        const [showRegister, setShowRegister] = useState(true);

    const handleLogout = () => {
        localStorage.removeItem("token");
        setIsLoggedIn(false);
        alert("Logged out successfully!");
    };


    const [products, setProducts] = useState([]);
  const [cart, setCart] = useState([]);
  const [orders, setOrders] = useState([]);

  useEffect(() => {
      fetch(`${import.meta.env.VITE_API_URL}/api/products`)

          .then(response => response.json())
        .then(data => {
          setProducts(data);
        })
        .catch(error => {
          console.error("Error:", error);
        });
  }, []);
    useEffect(() => {
        fetch(`${import.meta.env.VITE_API_URL}/api/orders`, {

            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        })
            .then(response => response.json())
            .then(data => {
                setOrders(data);
            })
            .catch(error => {
                console.error("Orders error:", error);
            });
    }, []);


  const totalPrice = cart.reduce(
      (total,item)=> total+item.price*item.quantity,0
  );
    const placeOrder = async () => {
        try {
            const response = await fetch(`${import.meta.env.VITE_API_URL}/api/orders`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer"+ localStorage.getItem("token")
                },
                body: JSON.stringify({
                    user: {
                        id: 4
                    },
                    totalAmount: totalPrice,
                    orderStatus: "placed"
                })
            });

            const data = await response.json();

            console.log("Status:", response.status);
            console.log("Response:", data);

            if (!response.ok) {
                alert("Order failed. Check console.");
                return;
            }

            alert(`Order placed successfully! order id:${data.id}`);
            setCart([]);
        } catch (error) {
            console.error("Order error:", error);
            alert("Could not connect to backend");
        }
    };


    if (!isLoggedIn) {
        if (showRegister) {
            return (
                <Register
                    goToLogin={() => setShowRegister(false)}
                />
            );
        }

        return <Login setIsLoggedIn={setIsLoggedIn} />;
    }


    return (
      <div className="home-page">
          <button onClick={handleLogout}>LOG OUT</button>
        <h1>ಖರೀದಿಸಿ (kharidisi) 🛒</h1>
          <h2>My Orders</h2>

          <div>
              {orders.map(order => (
                  <div className="order-card" key={order.id}>
                      <div className="order-header">
                          <h3>📦 Order #{order.id}</h3>
                          <span className="order-status">{order.orderStatus}</span>
                      </div>

                      <div className="order-details">
                          <p>
                              <strong>Total:</strong> ₹{order.totalAmount}
                          </p>

                          <p>
                              <strong>Date:</strong>{" "}
                              {new Date(order.orderDate).toLocaleString()}
                          </p>
                      </div>
                  </div>
              ))}
          </div>

          <h2>ಪ್ರಾಡಕ್ಟ್ಸ್ (Products)</h2>
        <div className="products">
          {products.map(product => (
              <div className="product-card" key={product.id}>
                <h3>{product.name}</h3>
                <p>{product.description}</p>
                <p>Category: {product.category}</p>
                <button onClick={() => {
                    const existingItem = cart.find(item => item.id === product.id);

                    if (existingItem) {
                        setCart(
                            cart.map(item =>
                                item.id === product.id
                                    ? { ...item, quantity: item.quantity + 1 }
                                    : item
                            )
                        );
                    } else {
                        setCart([...cart, { ...product, quantity: 1 }]);
                    }
                }}>ADD To CART
                </button>
              </div>
          ))}
        </div>
          <div className="cart">
              <h2>CART</h2>
              {cart.length === 0&& <p>your cart is empty</p>}
              {cart.map((item, index) => (
                  <div key={index} className="cart-item">
                      <h3>{item.name}</h3>
                      <p>Price: ₹{item.price}</p>
                      <p>Subtotal: ₹{item.price*item.quantity}</p>
                      <div>
                          <div className="quantity-controls">

                              <button
                                  onClick={() => {
                                      if (item.quantity === 1) {
                                          setCart(cart.filter(cartItem => cartItem.id !== item.id));
                                      } else {
                                          setCart(
                                              cart.map(cartItem =>
                                                  cartItem.id === item.id
                                                      ? { ...cartItem, quantity: cartItem.quantity - 1 }
                                                      : cartItem
                                              )
                                          );
                                      }
                                  }}
                              >
                                  −
                              </button>

                              <span>{item.quantity}</span>

                              <button
                                  onClick={() => {
                                      setCart(
                                          cart.map(cartItem =>
                                              cartItem.id === item.id
                                                  ? { ...cartItem, quantity: cartItem.quantity + 1 }
                                                  : cartItem
                                          )
                                      );
                                  }}
                              >
                                  +
                              </button>

                          </div>

                          <button
                              onClick={() => {
                                  setCart(cart.filter(cartItem => cartItem.id !== item.id));
                              }}
                          >
                              Remove
                          </button>


                      </div>



                  </div>
              ))}<h3>
              total:₹{totalPrice}
          </h3>
              {cart.length> 0 &&(  <button onClick={placeOrder}>place order</button>)}



          </div>
      </div>
  );
}

export default App;
