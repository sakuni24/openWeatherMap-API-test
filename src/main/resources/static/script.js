function getWeather() {
    const city = document.getElementById('city-input').value;
    fetch(`http://localhost:8080/weather?city=${city}`)
        .then(response => response.json())
        .then(data => {
            const tempInKelvin = data.main.temp;
            const tempInCelsius = (tempInKelvin - 273.15).toFixed(2);
            const humidity = data.main.humidity;
            const wind = data.wind.speed;
            const weather = data.weather[0].description;

            document.getElementById('city-name').textContent = city;
            document.getElementById('temperature').textContent = tempInCelsius;
            document.getElementById('humidity').textContent = humidity;
            document.getElementById('wind').textContent = wind;
            document.getElementById('weather').textContent = weather;
        })
        .catch(error => console.error('Error fetching weather data:', error));
}

function filterCities() {
    const input = document.getElementById('city-input');
    const suggestions = document.getElementById('suggestions');
    const query = input.value.trim();

    suggestions.innerHTML = '';
    suggestions.style.display = 'none';

    if (query.length < 3) return; // Only search after 3 characters

    fetch(`http://api.openweathermap.org/geo/1.0/direct?q=${query}&limit=5&appid=e5e2976d63454f55be7c53753aed0c06`)
        .then(response => response.json())
        .then(data => {
            if (data.length === 0) return;

            data.forEach(city => {
                const suggestion = document.createElement('div');
                suggestion.textContent = `${city.name}, ${city.country}`;
                suggestion.className = 'suggestion-item';
                suggestion.onclick = () => {
                    input.value = `${city.name}, ${city.country}`;
                    suggestions.style.display = 'none'; // Hide suggestions
                };
                suggestions.appendChild(suggestion);
            });

            suggestions.style.display = 'block';
        })
        .catch(error => console.error('Error fetching city data:', error));
}

document.getElementById('city-input').addEventListener('input', filterCities);


