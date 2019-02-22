package one.example.com.mysample.main.db.entity;

public class TopMovieListInfoEntity {
    private String id;
    private String alt;//电影详情的网页
    private String year;//年份
    private String title;//名字
    private String original_title;//原标题
    private String collect_count;//集合计数
    private String subtype;//类型（电影或者小说）

    private String[] genres;//内容类型
    private String[] images;//图片

    private Director[] directors;//导演
    private Director[] Cast;//演员
    private Director[] rating;//演员


    //导演
    class Director {

    }

    //演员
    class Cast {

    }

    //评分
    class Rating {

    }



    /*

       {
		"rating": {
			"max": 10,
			"average": 9.6,
			"stars": "50",
			"min": 0
		},

		"casts": [{
			"alt": "https:\/\/movie.douban.com\/celebrity\/1054521\/",
			"avatars": {
				"small": "https://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p17525.webp",
				"large": "https://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p17525.webp",
				"medium": "https://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p17525.webp"
			},
			"name": "\u8482\u59c6\u00b7\u7f57\u5bbe\u65af",
			"id": "1054521"
		}, {
			"alt": "https:\/\/movie.douban.com\/celebrity\/1054534\/",
			"avatars": {
				"small": "https://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p34642.webp",
				"large": "https://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p34642.webp",
				"medium": "https://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p34642.webp"
			},
			"name": "\u6469\u6839\u00b7\u5f17\u91cc\u66fc",
			"id": "1054534"
		}, {
			"alt": "https:\/\/movie.douban.com\/celebrity\/1041179\/",
			"avatars": {
				"small": "https://img1.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p5837.webp",
				"large": "https://img1.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p5837.webp",
				"medium": "https://img1.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p5837.webp"
			},
			"name": "\u9c8d\u52c3\u00b7\u5188\u987f",
			"id": "1041179"
		}],

		"directors": [{
			"alt": "https:\/\/movie.douban.com\/celebrity\/1047973\/",
			"avatars": {
				"small": "https://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p230.webp",
				"large": "https://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p230.webp",
				"medium": "https://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p230.webp"
			},
			"name": "\u5f17\u5170\u514b\u00b7\u5fb7\u62c9\u90a6\u7279",
			"id": "1047973"
		}],

		"images": {
			"small": "https://img3.doubanio.com\/view\/photo\/s_ratio_poster\/public\/p480747492.webp",
			"large": "https://img3.doubanio.com\/view\/photo\/s_ratio_poster\/public\/p480747492.webp",
			"medium": "https://img3.doubanio.com\/view\/photo\/s_ratio_poster\/public\/p480747492.webp"
		},
		"genres": ["\u72af\u7f6a", "\u5267\u60c5"],





		"subtype": "movie",
        "collect_count": 1686202,
		"original_title": "The Shawshank Redemption",
		"title": "\u8096\u7533\u514b\u7684\u6551\u8d4e",
		"year": "1994",
		"alt": "https:\/\/movie.douban.com\/subject\/1292052\/",
		"id": "1292052"
	}


     */
}
