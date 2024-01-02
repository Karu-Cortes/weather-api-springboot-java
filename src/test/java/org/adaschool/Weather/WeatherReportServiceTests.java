package org.adaschool.Weather;

import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;


public class WeatherReportServiceTests {

    @Mock
    private RestTemplate restTemplate;
    private WeatherReportService weatherReportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        weatherReportService = new WeatherReportService(restTemplate);
    }

    @Test
    public void testGetWeatherReportWithBDDMockito() throws Exception {
        double latitude = 37.8267;
        double longitude = -122.4233;

        WeatherApiResponse simulatedApiResponse = new WeatherApiResponse();
        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(25.0);
        main.setHumidity(60.0);
        simulatedApiResponse.setMain(main);

        //Construcción de URL con las coordenadas y la clave secreta
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=37.8267&lon=-122.4233&appid=18f375da1a039c83d3ba963803173a18";

        given(restTemplate.getForObject(apiUrl, WeatherApiResponse.class))
                .willReturn(simulatedApiResponse);

        WeatherReport response = weatherReportService.getWeatherReport(latitude, longitude);

        // Asserts with BDDMockito
        assertThat(response.getTemperature()).isEqualTo(25.0);
        assertThat(response.getHumidity()).isEqualTo(60.0);
    }
}