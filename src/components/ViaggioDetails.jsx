import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const ViaggioDetails = () => {
  const { id } = useParams();
  const [viaggio, setViaggio] = useState(null);
  const [prenotato, setPrenotato] = useState(false);
  const token = localStorage.getItem("token");
  const [user, setUser] = useState(null);
  const username = localStorage.getItem("username");

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
          console.log(data);
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
          console.log(data);
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
        console.log("Prenotazione effettuata con successo!");
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
        <div className="row">
          <div className="col-md-5">
            <div className="titlepage">
              <h2>{viaggio.citta}</h2>
              <p>{viaggio.stato}</p>
              <p>{viaggio.descrizione}</p>
              <p> data di partenza: {viaggio.dataPartenza}</p>

              <p>{viaggio.prezzo}</p>
              {!prenotato && (
                <button className="read_more" onClick={handlePrenota}>
                  Prenota
                </button>
              )}
              {prenotato && <p>Prenotazione effettuata con successo!</p>}
            </div>
          </div>
          <div className="col-md-7">
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
