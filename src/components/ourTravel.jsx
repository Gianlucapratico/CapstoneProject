import React, { useEffect, useState } from "react";
import ViaggioDetails from "./ViaggioDetails";
import { Link } from "react-router-dom";

const OurTravel = () => {
  const [viaggi, setViaggi] = useState([]);
  const [token, setToken] = useState("");
  const [errore, setErrore] = useState("");
  const [selectedViaggio, setSelectedViaggio] = useState(null);

  const selectViaggio = (viaggio) => {
    setSelectedViaggio(viaggio);
  };

  useEffect(() => {
    const token = localStorage.getItem("token");
    const fetchViaggi = async () => {
      try {
        const response = await fetch("http://localhost:3001/api/viaggi", {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
        });

        if (response.ok) {
          const data = await response.json();
          setViaggi(data.content);
        } else {
          throw new Error("Errore durante la richiesta dei viaggi");
        }
      } catch (error) {
        console.error("Errore durante la richiesta dei viaggi:", error);
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
                      <figure>
                        <img src={viaggio.urlImg} alt={viaggio.citta} />
                      </figure>
                    </Link>
                  </div>
                  <div className="bed_room">
                    <h3>{viaggio.citta}</h3>
                    <p>{viaggio.stato}</p>
                    <p>{viaggio.prezzo}€</p>
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
