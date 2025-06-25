<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Finished Matches</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
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
        <h1>Matches</h1>
        <div class="input-container">
            <form action="${pageContext.request.contextPath}/matches" method="GET">
                <label for="filter">Filter by name</label>
                <input class="input-filter" type="text" placeholder="Player name" name="filter_by_player_name" id="filter">
            </form>

            <div>
                <a href="${pageContext.request.contextPath}/matches">
                    <button class="btn-filter">Reset Filter</button>
                </a>
            </div>
        </div>
        <table class="table-matches">
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>

            <c:forEach items="${requestScope.matches}" var="match">
                <tr>
                    <td>${match.player1.name}</td>
                    <td>${match.player2.name}</td>
                    <td><span class="winner-name-td">${match.winner.name}</span></td>
                </tr>
            </c:forEach>

        </table>

        <c:set var="page" value="${empty param.page ? 1 : Integer.parseInt(param.page)}"/>
        <c:set var="filterName" value="${param.filter_by_player_name}"/>

        <div class="pagination">
            <c:if test="${page > 1}">
                <c:url var="prevUrl" value="/matches">
                    <c:param name="page" value="${page - 1}"/>
                    <c:if test="${not empty filterName}">
                        <c:param name="filter_by_player_name" value="${filterName}"/>
                    </c:if>
                </c:url>
                <a class="prev" href="${prevUrl}">< Назад</a>
            </c:if>

            <c:url var="nextUrl" value="/matches">
                <c:param name="page" value="${page + 1}"/>
                <c:if test="${not empty filterName}">
                    <c:param name="filter_by_player_name" value="${filterName}"/>
                </c:if>
            </c:url>
            <a class="next" href="${nextUrl}">Вперед ></a>
        </div>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
            roadmap.</p>
    </div>
</footer>
</body>
</html>
