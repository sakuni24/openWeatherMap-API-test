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
