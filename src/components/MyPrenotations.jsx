import React, { useEffect, useState } from "react";
import { Modal } from "react-bootstrap";
import Recensioni from "./Recensioni";
import Stars from "./Stars";

const MyPrenotations = () => {
  const [prenotazioni, setPrenotazioni] = useState([]);
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [showRecensioneModal, setShowRecensioneModal] = useState(false);
  const [showDeleteModal, setShowDeleteModal] = useState(false);
  const [selectedPrenotazioneId, setSelectedPrenotazioneId] = useState(null);
  const [selectedRecensione, setSelectedRecensione] = useState(null);
  const [recensioni, setRecensioni] = useState([]);

  const handleOpenRecensione = async (prenotazioneId) => {
    setSelectedPrenotazioneId(prenotazioneId);
    try {
      const token = localStorage.getItem("token");
      const response = await fetch(
        `http://localhost:3001/api/recensioni?prenotazioneId=${prenotazioneId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );

      if (response.ok) {
        const data = await response.json();
        const recensioni = data.content;
        setRecensioni(recensioni);
        setShowRecensioneModal(true);
      } else {
        throw new Error("Errore durante il recupero delle recensioni");
      }
    } catch (error) {
      console.error("Errore durante il recupero delle recensioni:", error);
    }
  };

  const handleOpenRecensioneForm = (prenotazioneId) => {
    setSelectedPrenotazioneId(prenotazioneId);
    setSelectedRecensione(null); // Resetta il form delle recensioni
    setShowRecensioneModal(true);
  };

  const handleDeletePrenotazione = async (prenotazioneId) => {
    setSelectedPrenotazioneId(prenotazioneId);
    setShowDeleteModal(true);
  };

  const fetchPrenotazioni = async () => {
    try {
      const responsePrenotazioni = await fetch(
        "http://localhost:3001/api/prenotazioni",
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );

      if (responsePrenotazioni.ok) {
        const dataPrenotazioni = await responsePrenotazioni.json();
        setPrenotazioni(dataPrenotazioni.content);
      } else {
        throw new Error("Errore durante il recupero delle prenotazioni");
      }
    } catch (error) {
      console.error(
        "Errore durante il recupero delle prenotazioni e recensioni:",
        error
      );
    }
  };

  useEffect(() => {
    fetchPrenotazioni();
  }, [token, prenotazioni]);

  const confirmDeletePrenotazione = async () => {
    if (!selectedPrenotazioneId) return;

    try {
      const response = await fetch(
        `http://localhost:3001/api/prenotazioni/${selectedPrenotazioneId}`,
        {
          method: "DELETE",
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
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
    setShowDeleteModal(false);
  };

  const handleCloseModal = () => {
    setSelectedPrenotazioneId(null);
    setShowDeleteModal(false);
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
                      className="read_more2 mt-3"
                      onClick={() => handleDeletePrenotazione(prenotazione.id)}
                    >
                      Cancella
                    </button>
                    <button
                      className="read_more2 mt-3 ml-2"
                      onClick={() => {
                        handleOpenRecensione(prenotazione.id);
                        setShowRecensioneModal(true);
                      }}
                    >
                      Recensione
                    </button>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
      {selectedPrenotazioneId && (
        <Recensioni
          prenotazioneId={selectedPrenotazioneId}
          prenotazione={prenotazioni.find(
            (prenotazione) => prenotazione.id === selectedPrenotazioneId
          )}
          setShowModal={setShowRecensioneModal}
          showModal={showRecensioneModal}
          setSelectedRecensione={setSelectedRecensione}
        />
      )}
      {/* Modal for confirmation */}
      <Modal show={showDeleteModal} onHide={handleCloseModal}>
        <Modal.Header closeButton>
          <Modal.Title>Conferma Eliminazione Prenotazione</Modal.Title>
        </Modal.Header>
        <Modal.Body>Sei sicuro di voler cancellare la prenotazione?</Modal.Body>
        <Modal.Footer>
          <button className="btn btn-secondary" onClick={handleCloseModal}>
            Annulla
          </button>
          <button
            className="btn btn-danger"
            onClick={confirmDeletePrenotazione}
          >
            Conferma
          </button>
        </Modal.Footer>
      </Modal>
      {prenotazioni.length > 0 && (
        <div className="back_re mb-5">
          <div className="container">
            <div className="row">
              <div className="col-md-12">
                <div className="title">
                  <h2>Recensioni</h2>
                </div>
              </div>
            </div>
          </div>
        </div>
      )}
      {prenotazioni ? (
        prenotazioni.map(
          (prenotazione) =>
            prenotazione.recensione.length > 0 && (
              <div
                className="d-flex justify-content-center"
                key={prenotazione.id}
              >
                <div className="mb-5">
                  <h3 className="recensioni">
                    Recensioni per la prenotazione:
                    {" " + prenotazione.viaggio.citta}
                  </h3>
                  {prenotazione.recensione.map((recensione) => {
                    return (
                      <>
                        <div
                          className="card-body text-center"
                          key={recensione.id}
                        >
                          <p className="card-title">
                            <b> Valutazione </b>
                            <p className="mt-2 rating-stars">
                              <Stars
                                starNumber={recensione.valutazione}
                              ></Stars>
                            </p>
                          </p>

                          <p className="card-text">
                            <b> Commento </b>
                            <p className=" commento mt-3 text-dark ">
                              {recensione.commento}
                            </p>
                          </p>
                          <hr />
                        </div>
                      </>
                    );
                  })}
                </div>
              </div>
            )
        )
      ) : (
        <div>Nessuna recensione disponibile</div>
      )}
    </div>
  );
};

export default MyPrenotations;
