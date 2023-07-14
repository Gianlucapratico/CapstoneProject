import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const ViaggioDetails = () => {
  const { id } = useParams();
  const [viaggio, setViaggio] = useState(null);

  useEffect(() => {
    const fetchViaggioDetails = async () => {
      try {
        const response = await fetch(`http://localhost:3001/api/viaggi/${id}`, {
          headers: {
            Authorization:
              "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJHaWFubHVjYSIsImlhdCI6MTY4OTMxODA5MiwiZXhwIjoxNjg5OTIyODkyfQ.T-UTgM3vkrQcJZf2rBSuplm2xGpFJvEBRAYrQJsZEwQ",
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

    fetchViaggioDetails();
  }, [id]);

  if (!viaggio) {
    return <div>Caricamento in corso...</div>;
  }

  return (
    <div className="about">
      <div className="container-fluid">
        <div className="row">
          <div className="col-md-5">
            <div className="titlepage">
              <h2>{viaggio.citta}</h2>
              <p>{viaggio.descrizione}</p>
              <p>{viaggio.prezzo}</p>
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
