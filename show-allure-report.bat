@echo off
local enabledelayedexpansion

echo ==============================
echo   🚀 Allure Report Generator
echo ==============================
echo.

REM Check if Allure CLI is installed
where allure >nul 2>nul
if %error level% neq 0 (
    echo ❌ Allure CLI not found!
    echo Please install it with: scoop install allure
    pause
    exit /b
)

REM Check if allure-results folder exists
if not exist "target\allure-results" (
    echo ❌ No Allure results found!
    echo Run your TestNG tests first to generate results in:
    echo target\allure-results\
    pause
    exit /b
)

echo 🧹 Cleaning previous report...
rmdir /s /q allure-report >nul 2>nul

echo 📊 Generating new Allure report...
allure generate target/allure-results -o allure-report --clean

if %error level% neq 0 (
    echo ❌ Failed to generate report.
    pause
    exit /b
)

echo 🌐 Opening Allure report in browser...
allure open allure-report

echo.
echo ✅ Done! Your Allure report should now be open.
pause


***/// Generate report => allure generate target/allure-results -o allure-report --clean ///***
***/// Run Allure report => allure open allure-report///***
