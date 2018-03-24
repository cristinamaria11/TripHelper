# TripHelper
Java application that helps people choose where to spend their next vacation.<br>
It uses files to read the locations and commands and also to write the results. The names of the files are given from stdin.

## Supported commands
- Display all - Prints all the locations, hierarchical.<br>
<br>
- Find location named:location_name - Prints all the information on the place named location_name<br>
<br>
- Find locations in city:city_name - Prints all the locations from the city named city_name<br>
<br>
- Find places by activity:activity_name - Prints all the places that have the activity activity_name, in the descending order of the price<br>
<br>
- Cheapest places in Country/County/City between:date1-date2 - Prints all the places from the specified location(Country/County/City) available between the given dates(date1, date2), in the descending order of the price<br>
<br>
- Cheapest places in Country/County/City - Prints all the places from the specified location(Country/County/City), in the descending order of the price<br>
<br>
The date format is dd/mm/yyyy<br>
	
## Input locations format
Every line from the locations file has the following format:<br> <br>
place_name;city_name;county_name;country_name;average_price_per_day;activities_list;available_dates;<br> <br>
The elements of the list of activities are separated by a colon and the available dates are also separated by the same mark.
	
## Observations

