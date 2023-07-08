import React, { useState, useEffect } from "react";
import OurTravel from "./ourTravel";

const ParentComponent = ({ viaggioId }) => {
  const [viaggi, setViaggi] = useState([]);
  const [selectedViaggio, setSelectedViaggio] = useState(null);

  const token =
    "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJHaWFubHVjYSIsImlhdCI6MTY4ODgyMjI5MCwiZXhwIjoxNjg5NDI3MDkwfQ.xSepmPHLjtpAgDNohfc3i1c28yb99UoGGRy8eYNrLTk";

  useEffect(() => {
    fetch("http://localhost:3001/api/viaggi", {
      headers: {
        Authorization: "Bearer " + token,
      },
    })
      .then((response) => response.json())
      .then((data) => setViaggi(data))
      .catch((error) =>
        console.error("Errore durante la richiesta dei viaggi:", error)
      );
  }, []);

  const handleViaggioClick = (viaggioId) => {
    fetch(`http://localhost:3001/api/viaggi/${viaggioId}`, {
      headers: {
        Authorization: "Bearer " + token,
      },
    })
      .then((response) => response.json())
      .then((data) => setSelectedViaggio(data))
      .catch((error) =>
        console.error("Errore durante la richiesta del viaggio:", error)
      );
  };

  useEffect(() => {
    if (viaggioId) {
      fetch(`http://localhost:3001/api/viaggi/${viaggioId}`, {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
        .then((response) => response.json())
        .then((data) => setSelectedViaggio(data))
        .catch((error) =>
          console.error("Errore durante la richiesta del viaggio:", error)
        );
    }
  }, [viaggioId]);

  return (
    <div>
      <OurTravel viaggi={viaggi} onViaggioSelect={handleViaggioClick} />

      {selectedViaggio && (
        <div>
          <h2>{selectedViaggio.title}</h2>
          <p>{selectedViaggio.description}</p>
          <img src={selectedViaggio.imageUrl} alt={selectedViaggio.title} />
        </div>
      )}
    </div>
  );
};

export default ParentComponent;
