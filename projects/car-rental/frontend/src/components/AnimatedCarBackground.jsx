// components/AnimatedCarBackground.jsx
import { useCallback } from "react";
import { Particles } from "react-tsparticles";
import { loadFull } from "tsparticles";
import { motion } from "framer-motion";

const carEmojis = ["🚗", "🚕", "🚙", "🚌", "🏎️", "🚓", "🚑", "🚒"];

const AnimatedCarBackground = () => {
  const particlesInit = useCallback(async (engine) => {
    await loadFull(engine);
  }, []);

  return (
    <div className="fixed inset-0 -z-10 overflow-hidden">
      {/* Particle Network */}
      <Particles
        id="tsparticles"
        init={particlesInit}
        options={{
          background: { color: "#111827" }, // dark blue-gray
          particles: {
            color: { value: "#ffffff" },
            links: { 
              color: "#3b82f6", // blue links
              distance: 150,
              enable: true 
            },
            move: { enable: true, speed: 1 },
            number: { value: 60 },
            opacity: { value: 0.3 }
          }
        }}
      />
      
      {/* Animated Car Emojis */}
      {Array.from({ length: 8 }).map((_, i) => (
        <motion.div
          key={i}
          initial={{
            x: Math.random() * window.innerWidth,
            y: Math.random() * window.innerHeight,
            rotate: Math.random() * 360
          }}
          animate={{
            x: [null, Math.random() * window.innerWidth],
            y: [null, Math.random() * window.innerHeight],
            rotate: [null, Math.random() * 360]
          }}
          transition={{
            duration: 20 + Math.random() * 20,
            repeat: Infinity,
            repeatType: "reverse",
            ease: "linear"
          }}
          className="absolute text-4xl pointer-events-none"
          style={{ zIndex: 1 }}
        >
          {carEmojis[i % carEmojis.length]}
        </motion.div>
      ))}
    </div>
  );
};

export default AnimatedCarBackground;