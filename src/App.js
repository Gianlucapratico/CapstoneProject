import { BrowserRouter, Routes, Route } from "react-router-dom";

import MyFooter from "./components/MyFooter";
import About from "./components/About";
import Banner from "./components/Banner";
import Header from "./components/Header";

import "./css/style.css";
import "./js/custom.js";
import Login from "./components/Login";
import Home from "./components/Home";
import SignIn from "./components/SignIn";
import OurTravel from "./components/OurTravel";
import ViaggioDetails from "./components/ViaggioDetails";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/sign in" element={<SignIn />} />
          <Route path="/ourtravel" element={<OurTravel />} />
          <Route path="/about" element={<About />} />
          <Route path="/viaggiodetails/:id" element={<ViaggioDetails />} />
          <Route path="/banner" element={<Banner />} />
        </Routes>
        <MyFooter />
      </BrowserRouter>
    </div>
  );
}

export default App;
