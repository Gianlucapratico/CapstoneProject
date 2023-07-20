import React, { useEffect, useState } from "react";

import { Modal } from "react-bootstrap";

const MyPrenotations = () => {
  const [prenotazioni, setPrenotazioni] = useState([]);
  const token = localStorage.getItem("token");
  const [showModal, setShowModal] = useState(false);
  const [selectedPrenotazioneId, setSelectedPrenotazioneId] = useState(null);

  useEffect(() => {
    const fetchPrenotazioni = async () => {
      try {
        const response = await fetch("http://localhost:3001/api/prenotazioni", {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        if (response.ok) {
          const data = await response.json();
          setPrenotazioni(data.content);
        } else {
          throw new Error("Errore durante il recupero delle prenotazioni");
        }
      } catch (error) {
        console.error("Errore durante il recupero delle prenotazioni:", error);
      }
    };

    fetchPrenotazioni();
  }, [token]);

  const handleDeletePrenotazione = async (prenotazioneId) => {
    setSelectedPrenotazioneId(prenotazioneId);
    setShowModal(true);
  };

  const handleConfirmDelete = async () => {
    if (!selectedPrenotazioneId) return;

    try {
      const response = await fetch(
        `http://localhost:3001/api/prenotazioni/${selectedPrenotazioneId}`,
        {
          method: "DELETE",
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (response.ok) {
        setPrenotazioni((prevPrenotazioni) =>
          prevPrenotazioni.filter(
            (prenotazione) => prenotazione.id !== selectedPrenotazioneId
          )
        );
        console.log("Prenotazione eliminata con successo!");
      } else {
        throw new Error("Errore durante l'eliminazione della prenotazione");
      }
    } catch (error) {
      console.error("Errore durante l'eliminazione della prenotazione:", error);
    }

    setSelectedPrenotazioneId(null);
    setShowModal(false);
  };

  const handleCloseModal = () => {
    setSelectedPrenotazioneId(null);
    setShowModal(false);
  };

  return (
    <div>
      <div className="back_re">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <div className="title">
                <h2>My Reservations</h2>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="back_re">{/* Rest of the code for the header... */}</div>
      {/* Check if prenotazioni is empty */}
      {prenotazioni.length === 0 && (
        <div className="d-flex justify-content-center align-items-center mt-5">
          <div className="text-center p-4" role="alert">
            <h4 className="alert-heading mb-5 display-3">
              Nessuna prenotazione disponibile!
            </h4>
            <p className="lead mb-0">
              Al momento non hai effettuato nessuna prenotazione. Inizia a
              esplorare i viaggi disponibili e prenota la tua prossima
              avventura!
            </p>
          </div>
        </div>
      )}
      <div className="our_room">
        <div className="container">
          <div className="row d-flex justify-content-center">
            {prenotazioni.map((prenotazione) => (
              <div className="col-md-4 col-sm-6" key={prenotazione.viaggio.id}>
                <div id="serv_hover" className="room">
                  <div className="room_img">
                    <figure>
                      <img
                        src={prenotazione.viaggio.urlImg}
                        alt={prenotazione.viaggio.citta}
                      />
                    </figure>
                  </div>
                  <div className="bed_room">
                    <h3>{prenotazione.viaggio.citta}</h3>
                    <p>{prenotazione.viaggio.stato}</p>

                    <button
                      className="read_more2 mt-3 "
                      onClick={() => handleDeletePrenotazione(prenotazione.id)}
                    >
                      Cancella
                    </button>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>

      {/* Modal for confirmation */}
      <Modal show={showModal} onHide={handleCloseModal}>
        <Modal.Header closeButton>
          <Modal.Title>Conferma Eliminazione Prenotazione</Modal.Title>
        </Modal.Header>
        <Modal.Body>Sei sicuro di voler cancellare la prenotazione?</Modal.Body>
        <Modal.Footer>
          <button className="btn btn-secondary" onClick={handleCloseModal}>
            Annulla
          </button>
          <button className="btn btn-danger" onClick={handleConfirmDelete}>
            Conferma
          </button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default MyPrenotations;
