\"}]}"

curl -X POST ^
  -H "Content-Type: application/json" ^
  -H "Authorization: Bearer %API_KEY%" ^
  -d "%JSON_DATA%" ^
  %API_URL%

endlocal
