package main

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"strconv"
	"strings"
)

type Movie struct {
	ID       int    `json:"id"`
	Title    string `json:"title"`
	Director string `json:"director"`
	Year     int    `json:"year"`
}

var movies = []Movie{
	{ID: 1, Title: "Inception", Director: "Christopher Nolan", Year: 2010},
	{ID: 2, Title: "The Matrix", Director: "Lana Wachowski, Lilly Wachowski", Year: 1999},
}

// Utility to respond with JSON
func respondJSON(w http.ResponseWriter, status int, data interface{}) {
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(status)
	json.NewEncoder(w).Encode(data)
}

// GET /movies
func getMovies(w http.ResponseWriter, r *http.Request) {
	respondJSON(w, http.StatusOK, movies)
}

// GET /movies/{id}
func getMovie(w http.ResponseWriter, r *http.Request) {
	id, err := extractID(r.URL.Path)
	if err != nil {
		http.Error(w, "Invalid ID", http.StatusBadRequest)
		return
	}
	for _, m := range movies {
		if m.ID == id {
			respondJSON(w, http.StatusOK, m)
			return
		}
	}
	http.NotFound(w, r)
}

// POST /movies
func createMovie(w http.ResponseWriter, r *http.Request) {
	var m Movie
	if err := json.NewDecoder(r.Body).Decode(&m); err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	m.ID = len(movies) + 1
	movies = append(movies, m)
	respondJSON(w, http.StatusCreated, m)
}

// PUT /movies/{id}
func updateMovie(w http.ResponseWriter, r *http.Request) {
	id, err := extractID(r.URL.Path)
	if err != nil {
		http.Error(w, "Invalid ID", http.StatusBadRequest)
		return
	}
	var updated Movie
	if err := json.NewDecoder(r.Body).Decode(&updated); err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	for i, m := range movies {
		if m.ID == id {
			movies[i].Title = updated.Title
			movies[i].Director = updated.Director
			movies[i].Year = updated.Year
			respondJSON(w, http.StatusOK, movies[i])
			return
		}
	}
	http.NotFound(w, r)
}

// DELETE /movies/{id}
func deleteMovie(w http.ResponseWriter, r *http.Request) {
	id, err := extractID(r.URL.Path)
	if err != nil {
		http.Error(w, "Invalid ID", http.StatusBadRequest)
		return
	}
	for i, m := range movies {
		if m.ID == id {
			movies = append(movies[:i], movies[i+1:]...)
			w.WriteHeader(http.StatusNoContent)
			return
		}
	}
	http.NotFound(w, r)
}

// Helper: Extract ID from URL
func extractID(path string) (int, error) {
	parts := strings.Split(path, "/")
	return strconv.Atoi(parts[len(parts)-1])
}

func main() {
	http.HandleFunc("/movies", func(w http.ResponseWriter, r *http.Request) {
		switch r.Method {
		case http.MethodGet:
			getMovies(w, r)
		case http.MethodPost:
			createMovie(w, r)
		default:
			http.Error(w, "Method Not Allowed", http.StatusMethodNotAllowed)
		}
	})

	http.HandleFunc("/movies/", func(w http.ResponseWriter, r *http.Request) {
		switch r.Method {
		case http.MethodGet:
			getMovie(w, r)
		case http.MethodPut:
			updateMovie(w, r)
		case http.MethodDelete:
			deleteMovie(w, r)
		default:
			http.Error(w, "Method Not Allowed", http.StatusMethodNotAllowed)
		}
	})

	fmt.Println("🎥 Movie API running at http://localhost:8080")
	log.Fatal(http.ListenAndServe(":8080", nil))
}
