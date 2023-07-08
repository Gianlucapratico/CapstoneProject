import React, { useState } from "react";
import ViaggioDetail from "./ViaggioDetail";
import ParentComponent from "./ParentComponent";

const OurTravel = () => {
  const [selectedViaggio, setSelectedViaggio] = useState(null);

  const handleViaggioClick = (viaggioId) => {
    setSelectedViaggio(viaggioId);
  };
  return (
    <div className="our_room">
      <div className="container">
        <div className="row">
          <div className="col-md-12">
            <div className="titlepage">
              <h2>Our Room</h2>
              <p>Lorem Ipsum available, but the majority have suffered </p>
            </div>
          </div>
        </div>
        <div className="row">
          <div className="col-md-4 col-sm-6">
            <div
              id="serv_hover"
              className="room"
              onClick={() => handleViaggioClick(1)}
            >
              <div className="room_img">
                <figure>
                  <img src={require("../images/room1.jpg")} alt="#" />
                </figure>
              </div>
              <div className="bed_room">
                <h3>Bed Room</h3>
                <p>
                  If you are going to use a passage of Lorem Ipsum, you need to
                  be sure there{" "}
                </p>
              </div>
            </div>
          </div>
          <div className="col-md-4 col-sm-6">
            <div
              id="serv_hover"
              className="room"
              onClick={() => handleViaggioClick(2)}
            >
              <div className="room_img">
                <figure>
                  <img src={require("../images/room2.jpg")} alt="#" />
                </figure>
              </div>
              <div className="bed_room">
                <h3>Bed Room</h3>
                <p>
                  If you are going to use a passage of Lorem Ipsum, you need to
                  be sure there{" "}
                </p>
              </div>
            </div>
          </div>
          <div className="col-md-4 col-sm-6">
            <div
              id="serv_hover"
              className="room"
              onClick={() => handleViaggioClick(3)}
            >
              <div className="room_img">
                <figure>
                  <img src={require("../images/room3.jpg")} alt="#" />
                </figure>
              </div>
              <div className="bed_room">
                <h3>Bed Room</h3>
                <p>
                  If you are going to use a passage of Lorem Ipsum, you need to
                  be sure there{" "}
                </p>
              </div>
            </div>
          </div>
          <div className="col-md-4 col-sm-6">
            <div
              id="serv_hover"
              className="room"
              onClick={() => handleViaggioClick(4)}
            >
              <div className="room_img">
                <figure>
                  <img src={require("../images/room4.jpg")} alt="#" />
                </figure>
              </div>
              <div className="bed_room">
                <h3>Bed Room</h3>
                <p>
                  If you are going to use a passage of Lorem Ipsum, you need to
                  be sure there{" "}
                </p>
              </div>
            </div>
          </div>
          <div className="col-md-4 col-sm-6">
            <div
              id="serv_hover"
              className="room"
              onClick={() => handleViaggioClick(5)}
            >
              <div className="room_img">
                <figure>
                  <img src={require("../images/room5.jpg")} alt="#" />
                </figure>
              </div>
              <div className="bed_room">
                <h3>Bed Room</h3>
                <p>
                  If you are going to use a passage of Lorem Ipsum, you need to
                  be sure there{" "}
                </p>
              </div>
            </div>
          </div>
          <div className="col-md-4 col-sm-6">
            <div
              id="serv_hover"
              className="room"
              onClick={() => handleViaggioClick(6)}
            >
              <div className="room_img">
                <figure>
                  <img src={require("../images/room6.jpg")} alt="#" />
                </figure>
              </div>
              <div className="bed_room">
                <h3>Bed Room</h3>
                <p>
                  If you are going to use a passage of Lorem Ipsum, you need to
                  be sure there{" "}
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
      {selectedViaggio && <ParentComponent viaggioId={selectedViaggio} />}
    </div>
  );
};
export default OurTravel;
