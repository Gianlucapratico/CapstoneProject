import React, { useEffect, useState } from "react";
import { Modal } from "react-bootstrap";
import Loader from "./Loader";

const Recensioni = ({
  prenotazioneId,
  prenotazione,

  deleteRecensione,
  createRecensione,
  updateRecensione,
  setShowModal, // Passa questa funzione per chiudere il modal
  showModal, // Passa questo stato per controllare quando mostrare il modal
}) => {
  const [commento, setCommento] = useState("");
  const [valutazione, setValutazione] = useState(0);
  const [showFormError, setShowFormError] = useState(false);
  const [recensioni, setRecensioni] = useState([]);
  const [selectedRecensione, setSelectedRecensione] = useState(null);

  const handleCloseModal = () => {
    setShowModal(false);
    setSelectedRecensione(null); // Resetta lo stato della recensione selezionata
  };

  const handleSubmitRecensione = async (e) => {
    e.preventDefault();

    // Controlla che la valutazione sia un numero compreso tra 1 e 5
    if (valutazione < 1 || valutazione > 5) {
      console.error("La valutazione deve essere un numero tra 1 e 5");
      return;
    }

    try {
      // Crea un oggetto con i dati della nuova recensione
      const newRecensioneData = {
        commento,
        valutazione,
        prenotazioneId: prenotazioneId,
      };

      // Effettua la richiesta POST per creare la nuova recensione
      const token = localStorage.getItem("token");
      const response = await fetch("http://localhost:3001/api/recensioni", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newRecensioneData),
      });

      if (response.ok) {
        console.log("Recensione creata con successo!");
        // Aggiorna la lista delle recensioni (se necessario) o visualizza la nuova recensione nella lista
        handleCloseModal();
      } else {
        console.error("Errore durante la creazione della recensione");
      }
    } catch (error) {
      console.error("Errore durante la creazione della recensione:", error);
    }
  };

  const handleModificaRecensione = (recensione) => {
    setSelectedRecensione(recensione);
    setShowModal(true);
  };

  const handleEliminaRecensione = async (recensioneId) => {
    try {
      await deleteRecensione(recensioneId);
      console.log("Recensione eliminata con successo!");
    } catch (error) {
      console.error("Errore durante l'eliminazione della recensione:", error);
    }
  };

  const handleRecensioneSubmit = async (recensioneData) => {
    if (!selectedRecensione) {
      // Aggiungi una nuova recensione
      await createRecensione({
        ...recensioneData,
        prenotazioneId: prenotazioneId, // Modifica la chiave in prenotazioneId
      });
      console.log("Recensione creata con successo!");
    } else {
      // Modifica una recensione esistente
      await updateRecensione(selectedRecensione.id, recensioneData);
      console.log("Recensione aggiornata con successo!");
    }

    setShowModal(false);
  };

  useEffect(() => {
    if (prenotazioneId) {
      const fetchRecensioni = async () => {
        try {
          const token = localStorage.getItem("token");
          const response = await fetch(
            `http://localhost:3001/api/recensioni?prenotazioneId=${prenotazioneId}`, // Modifica prenotazione in prenotazione.id
            {
              headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json",
              },
            }
          );

          if (!response.ok) {
            throw new Error("Errore durante il recupero delle recensioni");
          }

          const data = await response.json();
          setRecensioni(data.content);
        } catch (error) {
          console.error("Errore durante il recupero delle recensioni:", error);
        }
      };

      fetchRecensioni();
    }
  }, [prenotazioneId]);

  return (
    <>
      <Modal show={showModal} onHide={handleCloseModal}>
        <Modal.Header closeButton>
          <Modal.Title>
            {selectedRecensione ? "Modifica Recensione" : "Nuova Recensione"}
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {showFormError && (
            <div className="alert alert-danger" role="alert">
              Si prega di compilare tutti i campi obbligatori.
            </div>
          )}
          <div className="d-flex justify-content-between align-items-center">
            <label>Commento:</label>
            <textarea
              rows={5}
              cols={50}
              value={commento}
              onChange={(e) => setCommento(e.target.value)}
            ></textarea>
          </div>

          <div className="wrapperValutazione">
            <label className="">Valutazione:</label>
            <div class="rate">
              <input
                type="radio"
                id="star5"
                name="rate"
                value="5"
                onClick={(e) => setValutazione(e.target.value)}
              />
              <label for="star5" title="text"></label>
              <input
                type="radio"
                id="star4"
                name="rate"
                value="4"
                onClick={(e) => setValutazione(e.target.value)}
              />
              <label for="star4" title="text"></label>
              <input
                type="radio"
                id="star3"
                name="rate"
                value="3"
                onClick={(e) => setValutazione(e.target.value)}
              />
              <label for="star3" title="text"></label>
              <input
                type="radio"
                id="star2"
                name="rate"
                value="2"
                onClick={(e) => setValutazione(e.target.value)}
              />
              <label for="star2" title="text"></label>
              <input
                type="radio"
                id="star1"
                name="rate"
                value="1"
                onClick={(e) => setValutazione(e.target.value)}
              />
              <label for="star1" title="text"></label>
            </div>
          </div>
        </Modal.Body>
        <Modal.Footer>
          <button className="btn btn-secondary" onClick={handleCloseModal}>
            Annulla
          </button>
          <button className="btn btn-primary" onClick={handleSubmitRecensione}>
            {selectedRecensione ? "Salva Modifiche" : "Crea Recensione"}
          </button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default Recensioni;
