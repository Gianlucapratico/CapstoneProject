import { React, useEffect } from "react";
import $ from "jquery";

const Loader = () => {
  useEffect(() => {
    $(window).on("load", function () {
      $(".loader_bg").hide();
    });
  });

  return (
    <div className="loader_bg">
      <div className="loader">
        <img src={require("../images/loading.gif")} alt="#" />
      </div>
    </div>
  );
};

export default Loader;
