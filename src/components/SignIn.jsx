import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const SignIn = () => {
  const [name, setName] = useState("");
  const [surname, setSurname] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [alert, setAlert] = useState(null);
  const [emailError, setEmailError] = useState("");
  const navigate = useNavigate();
  const [redirect, setRedirect] = useState(false);

  const handleFormSubmit = async (e) => {
    e.preventDefault();

    if (!name || !surname || !username || !email || !password) {
      setAlert(
        "Errore durante la registrazione: tutti i campi sono obbligatori"
      );
      return;
    }

    const userData = {
      nome: name,
      cognome: surname,
      username,
      email,
      password,
    };

    const endpointURL = "http://localhost:3001/auth/register";
    const token =
      "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJHaWFubHVjYSIsImlhdCI6MTY4ODcxNzA4NSwiZXhwIjoxNjg5MzIxODg1fQ.31BgOCaLlYehrd-RsBsV-LgNrfecgQLQbVa0QZLrrNc";

    try {
      const response = await fetch(endpointURL, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(userData),
      });

      if (response.ok) {
        // Registrazione avvenuta con successo
        setAlert("Registrazione avvenuta con successo");
        setRedirect(true); // Imposta il valore di "redirect" a true

        setEmailError("");
        // Effettua eventuali azioni aggiuntive o reindirizzamenti
      } else {
        // Errore nella registrazione
        const errorData = await response.json();
        if (errorData && errorData.errors) {
          const errorMessage = Object.values(errorData.errors).join(". ");
          setAlert(`Errore durante la registrazione: ${errorMessage}`);
        }
        //if errorMessage == ("Email already in use!")
        if (
          errorData &&
          errorData.message &&
          errorData.message.includes("Email already in use!")
        ) {
          const errorMessage = `L'indirizzo email ${email} è già in uso`;
          setAlert(errorMessage);
        } else if (errorData && errorData.errors) {
          const errorMessage = Object.values(errorData.errors).join(". ");
          setAlert(`Errore durante la registrazione: ${errorMessage}`);
        } else {
          setAlert("Errore durante la registrazione");
        }
      }
    } catch (error) {
      console.error("Errore durante la richiesta di registrazione:", error);
      setAlert("Si è verificato un errore durante la registrazione");
    }
  };
  if (redirect) {
    navigate("/login"); // Naviga alla pagina di login
  }

  return (
    <div className="contact d-flex justify-content-center align-items-center">
      <div className="container">
        {alert && (
          <div
            className={`alert ${
              alert.startsWith("Errore") ? "alert-danger" : "alert-success"
            } d-flex justify-content-center align-items-center`}
            role="alert"
          >
            {alert.startsWith("Errore") ? (
              <span>{alert}</span>
            ) : (
              <span>Registrazione avvenuta con successo!</span>
            )}
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
                <div className="col-md-12">
                  <input
                    className="contactus"
                    placeholder="Name"
                    type="text"
                    name="Name"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                  />
                </div>
                <div className="col-md-12">
                  <input
                    className="contactus"
                    placeholder="Surname"
                    type="text"
                    name="Surname"
                    value={surname}
                    onChange={(e) => setSurname(e.target.value)}
                  />
                </div>
                <div className="col-md-12">
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
                    className={`contactus ${emailError ? "is-invalid" : ""}`}
                    placeholder="Email"
                    type="text"
                    name="Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                  />
                </div>
                {emailError && (
                  <div className="invalid-feedback">{emailError}</div>
                )}
                <div className="col-md-12">
                  <input
                    className="contactus"
                    placeholder="Password"
                    type="password"
                    name="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                  />
                  {password.length > 0 && password.length < 5 && (
                    <div className="invalid-feedback">
                      La password deve avere almeno 5 caratteri.
                    </div>
                  )}
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
  );
};

export default SignIn;
