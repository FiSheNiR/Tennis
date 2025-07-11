<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Match Score</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@300&display=swap" rel="stylesheet">
    <style><%@ include file="/css/style.css"%></style>
    <style><%@ include file="/js/app.js"%></style>

    <script src="js/app.js"></script>
</head>
<body>
<header class="header">

    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="${pageContext.request.contextPath}/images/menu.jpg" alt="Logo" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/matches">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <div class="container">
        <h1>Current match</h1>
        <div class="current-match-image"></div>
        <section class="score">
            <table class="table">
                <thead class="result">
                <tr>
                    <th class="table-text">Player</th>
                    <th class="table-text">Sets</th>
                    <th class="table-text">Games</th>
                    <th class="table-text">Points</th>
                </tr>
                </thead>
                <tbody>
                <tr class="player1">
                    <td class="table-text">${requestScope.currentMatch.player1.name}</td>
                    <td class="table-text">${requestScope.currentMatch.player1Score.setWins}</td>
                    <td class="table-text">${requestScope.currentMatch.player1Score.gameWins}</td>
                    <td class="table-text">${requestScope.currentMatch.player1Score.points}</td>
                    <td class="table-text">
                        <form  action="${pageContext.request.contextPath}/match-score?uuid=${requestScope.currentMatch.uuid}" method="post">
                            <input type="hidden" name="playerId" value="1">
                            <button class="score-btn">Score</button>
                        </form>
                    </td>
                </tr>
                <tr class="player2">
                    <td class="table-text">${requestScope.currentMatch.player2.name}</td>
                    <td class="table-text">${requestScope.currentMatch.player2Score.setWins}</td>
                    <td class="table-text">${requestScope.currentMatch.player2Score.gameWins}</td>
                    <td class="table-text">${requestScope.currentMatch.player2Score.points}</td>
                    <td class="table-text">
                        <form  action="${pageContext.request.contextPath}/match-score?uuid=${requestScope.currentMatch.uuid}" method="post">
                            <input type="hidden" name="playerId" value="2">
                            <button class="score-btn">Score</button>
                        </form>
                    </td>
                </tr>
                </tbody>
            </table>
        </section>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a> roadmap.</p>
    </div>
</footer>
</body>
</html>
