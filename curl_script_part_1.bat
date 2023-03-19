@echo off
setlocal

set "API_URL=https://chatgpt-api.shn.hk/v1/"

set "JSON_DATA={\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"