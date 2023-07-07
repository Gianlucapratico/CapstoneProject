import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [alert, setAlert] = useState(null);

  const handleFormSubmit = async (e) => {
    e.preventDefault();

    const userData = {
      username,
      password,
    };

    const endpointURL = "http://localhost:3001/auth/login";

    try {
      const response = await fetch(endpointURL, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(userData),
      });

      if (response.ok) {
        // Login avvenuto con successo
        setAlert("Login avvenuto con successo");
        navigate("/"); // Naviga alla pagina Home

        // Effettua eventuali azioni aggiuntive o reindirizzamenti
      } else {
        const errorData = await response.json();
        if (errorData.message === "User not found") {
          setAlert("Utente non trovato");
        } else if (errorData.message === "Invalid password") {
          setAlert("Password errata");
        } else {
          setAlert("Errore durante il login");
        }
      }
    } catch (error) {
      console.error("Errore durante la richiesta di login:", error);
      setAlert("Si è verificato un errore durante il login");
    }
  };

  return (
    <>
      <div className="contact d-flex justify-content-center align-items-center">
        <div className="container">
          {alert && (
            <div
              className={`alert ${
                alert.startsWith("Errore") ? "alert-danger" : "alert-success"
              } d-flex justify-content-center align-items-center`}
              role="alert"
            >
              <span>{alert}</span>
            </div>
          )}
          <div className="row justify-content-center">
            <div className="col-md-6">
              <form
                id="request"
                className="main_form"
                onSubmit={handleFormSubmit}
              >
                <div className="row">
                  <div className="col-md-12 ">
                    <input
                      className="contactus"
                      placeholder="Username"
                      type="text"
                      name="Username"
                      value={username}
                      onChange={(e) => setUsername(e.target.value)}
                    />
                  </div>
                  <div className="col-md-12">
                    <input
                      className="contactus"
                      placeholder="Password"
                      type="password"
                      name="Password"
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                    />
                  </div>

                  <div className="col-md-12">
                    <button className="send_btn" type="submit">
                      Send
                    </button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Login;
