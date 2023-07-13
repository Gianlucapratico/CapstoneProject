import React, { useEffect, useState } from "react";

const OurTravel = () => {
  const [viaggi, setViaggi] = useState([]);
  const [errore, setErrore] = useState("");

  const generateRandomImageUrl = () => {
    const width = 300; // Larghezza desiderata dell'immagine
    const height = 200; // Altezza desiderata dell'immagine
    const randomNumber = Math.floor(Math.random() * 100); // Numero casuale per rendere unico l'URL

    return `https://source.unsplash.com/random/${width}x${height}?${randomNumber}`;
  };

  useEffect(() => {
    console.log("prova");
    const fetchViaggi = async () => {
      try {
        const response = await fetch("http://localhost:3001/api/viaggi", {
          headers: {
            Authorization:
              "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJHaWFubHVjYSIsImlhdCI6MTY4OTIzNzgxNCwiZXhwIjoxNjg5ODQyNjE0fQ.8rIS-yBoliq0YEMIqzssX9DfnO_Up6TQi_056UQmpWM",
          },
        });
        if (response.ok) {
          const data = await response.json();
          setViaggi(data.content);
          console.log("Dati viaggi:", data.content);
        } else {
          throw new Error("Errore durante la richiesta dei viaggi");
        }
      } catch (errore) {
        console.error("Errore durante la richiesta dei viaggi:", errore);
        setErrore("Si è verificato un errore durante il recupero dei viaggi.");
      }
    };

    fetchViaggi();
  }, []);

  return (
    <div>
      <div className="back_re">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <div className="title">
                <h2>Our Travels</h2>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="our_room">
        <div className="row">
          {viaggi.map((viaggio) => (
            <div className="col-md-4 col-sm-6" key={viaggio.id}>
              <div id="serv_hover" className="room">
                <div className="room_img">
                  <figure>
                    <img src={generateRandomImageUrl()}></img>
                  </figure>
                </div>
                <div className="bed_room">
                  <h3>{viaggio.citta}</h3>
                  <p>{viaggio.descrizione} </p>
                  <p>{viaggio.prezzo} </p>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default OurTravel;
