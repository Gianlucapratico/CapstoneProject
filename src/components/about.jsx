import { Link } from "react-router-dom";

const About = () => {
  return (
    <>
      <section className="banner_main">
        <div className="homeHeader">
          <p className="homeHeader-title">Vivi viaggi unici nel mondo</p>
          <div className="buttonWrapper ">
            <Link to="/login">
              <button className="read_more" type="button">
                Login
              </button>
            </Link>

            <Link to="/sign-in">
              <button className="read_more" type="button">
                Register
              </button>
            </Link>
          </div>
        </div>

        <div className="container"></div>
      </section>
      <div className="about">
        <div className="container-fluid">
          <div className="row">
            <div className="col-md-5">
              <div className="titlepage">
                <h2>About Us</h2>
                <p>
                  Benvenuti nel mondo dello sviluppatore web! Sono appassionato
                  di creare esperienze digitali coinvolgenti e funzionali. Con
                  competenze in HTML, CSS JavaScript, React, Java e SpringBoot
                  trasformo idee in realtà, costruendo siti web belli e
                  responsivi. Il mio obiettivo è offrire soluzioni intuitive e
                  ottimizzate, garantendo una navigazione fluida e un design
                  accattivante. Con un'attenzione ai dettagli e la voglia di
                  imparare costantemente nuove tecnologie, sono pronto ad
                  affrontare qualsiasi sfida.La collaborazione e la
                  comunicazione sono per me fondamentali per comprendere appieno
                  le esigenze dei clienti e tradurle in soluzioni digitali di
                  successo. Sia che tu abbia bisogno di un sito web statico,
                  un'applicazione web complessa o un e-commerce dinamico, sono
                  qui per rendere la tua visione una realtà digitale. Lavoriamo
                  insieme per creare un impatto duraturo sul web! Sono
                  entusiasta di creare e costruire, un codice alla volta.
                  Contattami per discutere il tuo prossimo progetto e iniziare
                  questa avventura di sviluppo web insieme!
                </p>
              </div>
            </div>
            <div className="col-md-7">
              <div className="about_img">
                <figure>
                  <img src={require("../images/Lago.jpg")} alt="#" />
                </figure>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
export default About;
