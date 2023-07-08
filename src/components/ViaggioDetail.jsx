import React, { useEffect, useState } from "react";

const ViaggioDetail = ({ viaggioId }) => {
  const [viaggio, setViaggio] = useState(null);
  const token =
    "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJHaWFubHVjYSIsImlhdCI6MTY4ODgyMjE3MCwiZXhwIjoxNjg5NDI2OTcwfQ.qQS4IU5CmF18C0kjI6ODouJH1VXXGHLu5SAWd1JZrmE";

  /*useEffect(() => {
    // Effettua la chiamata Fetch per ottenere i dettagli del viaggio
    console.log("viaggioId:", viaggioId);
    fetch(`http://localhost:3001/api/viaggi/${viaggioId}`, {
      headers: {
        Authorization: "Bearer " + token,
      },
    })
      .then((response) => response.json())
      .then((data) => setViaggio(data))
      .catch((error) =>
        console.error("Errore durante la richiesta del viaggio:", error)
      );
  }, [viaggioId]);
*/
  if (!viaggio) {
    return <div>Loading...</div>;
  }

  return (
    <div className="col-md-8">
      <div className="about">
        <div className="container-fluid">
          <div className="row">
            <div className="col-md-5">
              <div className="titlepage">
                <h2>{viaggio.title}</h2>
                <p>{viaggio.description}</p>
                <a className="read_more" href="Javascript:void(0)">
                  Read More
                </a>
              </div>
            </div>
            <div className="col-md-7">
              <div className="about_img">
                <figure>
                  <img src={viaggio.imageUrl} alt={viaggio.title} />
                </figure>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ViaggioDetail;
