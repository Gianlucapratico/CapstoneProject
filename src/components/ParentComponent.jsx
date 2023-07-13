import React, { useState } from "react";
import OurTravel from "./OurTravel";

const ParentComponent = () => {
  const [viaggi, setViaggi] = useState([]);
  const [errore, setErrore] = useState("");

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
        setViaggi(data);
      } else {
        throw new Error("Errore durante la richiesta dei viaggi");
      }
    } catch (error) {
      console.error("Errore durante la richiesta dei viaggi:", error);
      setErrore("Si è verificato un errore durante il recupero dei viaggi.");
    }
  };

  return (
    <div>
      <h1>I Miei Viaggi</h1>
      {errore ? (
        <p>{errore}</p>
      ) : viaggi.length > 0 ? (
        <OurTravel viaggi={viaggi} />
      ) : (
        <p>Nessun viaggio disponibile.</p>
      )}
    </div>
  );
};

export default ParentComponent;
