import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { Alert } from "react-bootstrap";

const ViaggioDetails = () => {
  const { id } = useParams();
  const [viaggio, setViaggio] = useState(null);
  const [prenotato, setPrenotato] = useState(false);
  const token = localStorage.getItem("token");
  const [user, setUser] = useState(null);
  const username = localStorage.getItem("username");
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchViaggioDetails = async () => {
      try {
        const response = await fetch(`http://localhost:3001/api/viaggi/${id}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        if (response.ok) {
          const data = await response.json();
          setViaggio(data);
        } else {
          throw new Error(
            "Errore durante il recupero dei dettagli del viaggio"
          );
        }
      } catch (error) {
        console.error(
          "Errore durante il recupero dei dettagli del viaggio:",
          error
        );
      }
    };

    const fetchUserByUsername = async () => {
      try {
        const response = await fetch(
          `http://localhost:3001/users/username/${username}`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        if (response.ok) {
          const data = await response.json();
          setUser(data);
        } else {
          throw new Error(
            "Errore durante il recupero dei dettagli dell'utente"
          );
        }
      } catch (error) {
        console.error(
          "Errore durante il recupero dei dettagli dell'utente:",
          error
        );
      }
    };

    fetchViaggioDetails();
    fetchUserByUsername();
  }, [id, token, username]);

  const handlePrenota = async () => {
    setError(null);
    if (!viaggio || !user) {
      console.error("Viaggio o utente non valido");
      return;
    }

    const prenotazioneData = {
      viaggio: {
        id: viaggio.id,
        citta: viaggio.citta,
        stato: viaggio.stato,
        dataPartenza: viaggio.dataPartenza,
        dataArrivo: viaggio.dataArrivo,
        descrizione: viaggio.descrizione,
        prezzo: viaggio.prezzo,
        urlImg: viaggio.urlImg,
      },
      dataPrenotazione: "2023-07-20", // Imposta la data di prenotazione desiderata
      user: {
        id: user.id,
        nome: user.nome,
        cognome: user.cognome,
        email: user.email,
        password: user.password,
        username: user.username,
      },
    };

    try {
      const response = await fetch("http://localhost:3001/api/prenotazioni", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(prenotazioneData),
      });

      if (response.ok) {
        setPrenotato(true);
      } else {
        throw new Error("Errore durante la prenotazione del viaggio");
      }
    } catch (error) {
      console.error("Errore durante la prenotazione del viaggio:", error);
    }
  };

  if (!viaggio || !user) {
    return <div>Caricamento in corso...</div>;
  }

  return (
    <div className="about">
      <div className="container">
        {/* Centering container for the Alert */}
        <div className="d-flex justify-content-center  mb-5">
          {/* Alert */}
          {prenotato && (
            <Alert variant="success">
              Prenotazione effettuata con successo!
            </Alert>
          )}
          {error && <Alert variant="danger">{error}</Alert>}
        </div>
        <div className="row">
          <div className="col-md-5 order-md-2">
            <div className="titlepage">
              <h2>{viaggio.citta}</h2>
              <p>{viaggio.stato}</p>
              <p>{viaggio.descrizione}</p>
              <p> data di partenza: {viaggio.dataPartenza}</p>

              <p>{viaggio.prezzo}€</p>
              <Link to="/ourtravel" className="read_more mr-3 mb-3">
                Indietro
              </Link>
              {!prenotato && (
                <button className="read_more" onClick={handlePrenota}>
                  Prenota
                </button>
              )}
            </div>
          </div>
          <div className="col-md-7 order-md-1">
            <div className="about_img">
              <figure>
                <img src={viaggio.urlImg} alt={viaggio.citta} />
              </figure>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ViaggioDetails;
