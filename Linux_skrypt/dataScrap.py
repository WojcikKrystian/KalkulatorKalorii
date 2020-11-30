import requests, csv, re
from decimal import Decimal
from bs4 import BeautifulSoup, NavigableString, Tag

url = "http://oblicz-bmi.pl/tabela-kalorii.html"

req = requests.get(url)
req.encoding = "utf-8"

soup = BeautifulSoup(req.text, "html.parser")

tables = soup("table")

output_rows = []

for table in tables:
 for row in table:
  if isinstance(row, NavigableString):
    continue
  if isinstance(row, Tag):
    columns = row.findAll('td')
    output_row = []
    for column in columns:
      output_row.append(column.text)
    output_rows.append(output_row)

for row in output_rows[1:]:

   name = row[0].strip()   
#   kcal = Decimal(row[1].strip().replace(',','.')) #decimal
#   proteins = Decimal(row[2].strip().replace(',','.')) 
#   fat = Decimal(row[3].strip().replace(',','.')) 
#   carbs = Decimal(row[4].strip().replace(',','.'))

   kcal = str(row[1].strip().replace(',','.'))
   proteins = str(row[2].strip().replace(',','.'))
   fat = str(row[3].strip().replace(',','.'))
   carbs = str(row[4].strip().replace(',','.'))
   amount = str(100.0)
  
   print ("INSERT INTO ingredient(name, kcal, proteins, fat, carbs, amount) VALUES (" + '\'' + name + '\'' + "," + kcal + "," + proteins + "," + fat + "," + carbs + "," + amount + ");")
