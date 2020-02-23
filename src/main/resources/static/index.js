;!function () {
    const layPage = layui.laypage;
    const layer = layui.layer;
    const $ = layui.$;
    const size = 20;
    let page = 1;
    let layerShade;
    // 加载总页数
    $.ajax({
        url: '/blog/articleCount',
        type: 'GET',
        dataType: 'json',
        success: function (result) {
            layPage.render({
                elem: 'articlePage',
                count: result.data / size + 1, //数据总数
                jump: function (obj) {
                    page = obj.curr;
                    loadArticle();
                }
            });
        }
    });

    function loadArticle() {
        const url = '/blog/articles?page=' + page + '&size=' + size;
        $.ajax({
            url: url,
            type: 'GET',
            dataType: 'json',
            success: function (result) {
                showArticles(result.data);
            },
            beforeSend: function (XMLHttpRequest) {
                layerShade = layer.load(1, {
                    shade: [0.5, '#393D49']
                });
            },
            complete: function (XMLHttpRequest, textStatus) {
                // 关闭loading
                layer.close(layerShade);
            }
        });
    }

    function showArticles(articles) {
        console.log(articles);
        let articleHtml = "";
        for (let index in articles) {
            if (!articles.hasOwnProperty(index)) {
                continue;
            }
            let article = articles[index];
            const href = "/article?articleId=" + article["articleId"];
            const title = article["title"];
            const content = article["content"];
            const discussionCount = article["discussionCount"];
            const user = article["user"]["userName"];
            articleHtml += '<div class="layui-col-md12">';
            articleHtml += '<div class="layui-card">';
            articleHtml += '<div class="article-title article-title-pointer" onclick="window.location.href=\'' + href + '\'">' + title + '</div>';
            articleHtml += '<span class="article-info">评论数量: ' + discussionCount + '</span>';
            articleHtml += '<span class="article-info">作者: ' + user + '</span>';
            articleHtml += '<div class="layui-card-body">' + content + '</div>';
            articleHtml += '</div></div>';
        }
        $("#article-list").html(articleHtml);
    }
}();