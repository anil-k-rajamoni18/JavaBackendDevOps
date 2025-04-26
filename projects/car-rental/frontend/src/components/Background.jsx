import React from 'react';
import Particles from 'react-tsparticles';
import { loadFull } from 'tsparticles';

const Background = () => {
  const particlesInit = async (main) => {
    await loadFull(main);
  };

  return (
    <div id="background-container" className="absolute top-0 left-0 w-full h-full z-[-1]">
      <Particles
        id="tsparticles"
        init={particlesInit}
        options={{
          background: {
            color: { value: "#e0f7fa" }, // Light background color
          },
          fpsLimit: 60,
          interactivity: {
            events: {
              onClick: { enable: true, mode: "push" },
              onHover: { enable: true, mode: "repulse" },
              resize: true,
            },
            modes: {
              push: { quantity: 3 },
              repulse: { distance: 150, duration: 0.4 },
            },
          },
          particles: {
            number: {
              value: 50,  // More particles
              density: { enable: true, area: 800 },
            },
            color: {
              value: "#48dbfb",  // A nice blue color
            },
            shape: {
              type: "circle",
            },
            opacity: { value: 0.8, random: true },
            size: { value: { min: 5, max: 10 }, random: true },
            move: {
              enable: true,
              speed: 2,
              direction: "none",
              random: false,
              outModes: { default: "bounce" },
            },
            links: { enable: false },
          },
          detectRetina: true,
        }}
      />
    </div>
  );
};

export default Background;