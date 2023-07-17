import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const navigate = useNavigate();
  const [token, setToken] = useState("");

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [alert, setAlert] = useState(null);
  const [alertType, setAlertType] = useState(""); // Aggiunta variabile di stato per il tipo di alert

  const handleFormSubmit = async (e) => {
    e.preventDefault();

    if (!username || !password) {
      setAlert("Devi inserire tutti i campi"); // Imposta l'alert per i campi mancanti
      setAlertType("alert-danger"); // Imposta il tipo di alert a rosso
      return;
    }

    const userData = {
      username,
      password,
    };

    try {
      const response = await fetch("http://localhost:3001/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(userData),
      });

      if (response.ok) {
        // Login avvenuto con successo
        setAlert("Login avvenuto con successo");
        setAlertType("alert-success"); // Imposta il tipo di alert a verde
        const dataToken = await response.json();
        setToken(dataToken.accessToken);
        // Ottieni il token di autorizzazione dalla risposta

        localStorage.setItem("username", username);
        localStorage.setItem("token", dataToken.accessToken);
        if (dataToken.accessToken) navigate("/");
      }
      // Effettua eventuali azioni aggiuntive o reindirizzamenti
      else {
        const errorText = await response.text(); // Ottieni il testo dell'errore dalla risposta
        if (errorText === "Utente non trovato") {
          setAlert("Utente non trovato");
        } else if (errorText === "Username o password errati") {
          setAlert("Username o password errati");
        } else {
          setAlert("Errore durante il login");
        }
        setAlertType("alert-danger"); // Imposta il tipo di alert a rosso
      }
    } catch (error) {
      console.error("Errore durante la richiesta di login:", error);
      setAlert("Si è verificato un errore durante il login");
      setAlertType("alert-danger"); // Imposta il tipo di alert a rosso
    }
  };

  return (
    <>
      <div className="contact d-flex justify-content-center align-items-center">
        <div className="container">
          {alert && (
            <div
              className={`alert ${alertType} d-flex justify-content-center align-items-center`}
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

                  <div className="col-md-12 d-flex justify-content-end">
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
