import React, { useEffect, useState } from "react";
import ViaggioDetails from "./ViaggioDetails";
import { Link } from "react-router-dom";

const OurTravel = () => {
  const [viaggi, setViaggi] = useState([]);
  const [errore, setErrore] = useState("");
  const [selectedViaggio, setSelectedViaggio] = useState(null);

  const selectViaggio = (viaggio) => {
    console.log("Viaggio:", viaggio);

    setSelectedViaggio(viaggio);
  };

  useEffect(() => {
    const fetchViaggi = async () => {
      try {
        const response = await fetch("http://localhost:3001/api/viaggi", {
          headers: {
            Authorization:
              "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJHaWFubHVjYSIsImlhdCI6MTY4OTMxODA5MiwiZXhwIjoxNjg5OTIyODkyfQ.T-UTgM3vkrQcJZf2rBSuplm2xGpFJvEBRAYrQJsZEwQ",
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
        <div className="container">
          <div className="row">
            {viaggi.map((viaggio) => (
              <div className="col-md-4 col-sm-6" key={viaggio.id}>
                <div id="serv_hover" className="room">
                  <div className="room_img">
                    <Link to={`/viaggiodetails/${viaggio.id}`}>
                      <img src={viaggio.urlImg} alt={viaggio.citta} />
                    </Link>
                  </div>
                  <div className="bed_room">
                    <h3>{viaggio.citta}</h3>
                    <p>{viaggio.descrizione}</p>
                    <p>{viaggio.prezzo}</p>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
      {selectedViaggio && <ViaggioDetails viaggio={selectedViaggio} />}
    </div>
  );
};

export default OurTravel;
