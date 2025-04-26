import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import { AnimatePresence, motion } from 'framer-motion';
import AnimatedCarBackground from './components/AnimatedCarBackground';
import AnimatedBackground from './components/AnimatedBackground';
import Home from './pages/Home';
import AddCar from './pages/AddCar';
import RentCar from './pages/RentCar';
import './App.css';

const App = () => {
  return (
    <Router>
      <AnimatedCarBackground />
      <AnimatedBackground />
      
      <div className="relative container mx-auto p-4 min-h-screen">
        {/* Animated Title with Emoji */}
        <motion.div 
          initial={{ opacity: 0, y: -50 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 1, type: "spring" }}
          className="text-center mb-8"
        >
          <h1 className="text-5xl md:text-6xl font-extrabold bg-clip-text text-transparent bg-gradient-to-r from-blue-400 to-purple-600">
            <motion.span
              animate={{ rotate: [0, 10, -10, 0] }}
              transition={{ 
                duration: 3,
                repeat: Infinity,
                repeatType: "mirror"
              }}
              className="inline-block mr-3"
            >
              🚀
            </motion.span>
            <span className="relative">
              <span className="relative z-10">AutoVoyage</span>
              <motion.span
                initial={{ width: 0 }}
                animate={{ width: "100%" }}
                transition={{ duration: 1.5, delay: 0.5 }}
                className="absolute bottom-0 left-0 h-2 bg-yellow-400 z-0"
                style={{ originX: 0 }}
              />
            </span>
            <motion.span
              animate={{ rotate: [0, -10, 10, 0] }}
              transition={{ 
                duration: 3,
                repeat: Infinity,
                repeatType: "mirror",
                delay: 0.5
              }}
              className="inline-block ml-3"
            >
              🏎
            </motion.span>
          </h1>
          <motion.p
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            transition={{ delay: 1 }}
            className="text-xl text-white/80 mt-2"
          >
            The Future of Car Rental
          </motion.p>
        </motion.div>

        {/* Glassmorphism Navigation */}
        <motion.nav 
          initial={{ y: -100 }}
          animate={{ y: 0 }}
          transition={{ type: "spring", stiffness: 300 }}
          className="flex justify-between items-center mb-8 p-4 bg-white/10 backdrop-blur-md rounded-xl shadow-lg"
        >
          <Link to="/" className="text-white text-xl font-bold hover:text-blue-300 transition-colors">
            <motion.span whileHover={{ scale: 1.05 }}>Home</motion.span>
          </Link>
          <div className="flex space-x-6">
            <Link to="/add" className="text-white hover:text-green-300 transition-colors">
              <motion.span whileHover={{ scale: 1.1 }} className="flex items-center gap-2">
                <span>🚗</span> Add Car
              </motion.span>
            </Link>
            <Link to="/rent" className="text-white hover:text-purple-300 transition-colors">
              <motion.span whileHover={{ scale: 1.1 }} className="flex items-center gap-2">
                <span>🔑</span> Rent Car
              </motion.span>
            </Link>
          </div>
        </motion.nav>

        <AnimatePresence mode="wait">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/add" element={<AddCar />} />
            <Route path="/rent" element={<RentCar />} />
          </Routes>
        </AnimatePresence>
      </div>
    </Router>
  );
};

export default App;