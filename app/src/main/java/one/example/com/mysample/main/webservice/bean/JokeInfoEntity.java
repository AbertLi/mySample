package one.example.com.mysample.main.webservice.bean;


import java.util.List;

import one.example.com.mysample.main.db.entity.JokeEntity;
import one.example.com.mysample.main.webservice.BaseBean;

public class JokeInfoEntity extends BaseBean {


    /**
     * code : 200
     * message : 成功!
     * result : [{"sid":"29786816","text":"灵魂拷问，游戏和女朋友哪个更重要？","type":"video","thumbnail":"http://wimg.spriteapp.cn/picture/2019/0922/5d8721a3955fe_wpd.jpg","video":"http://wvideo.spriteapp.cn/video/2019/0922/5d8721a3955fe_wpd.mp4","images":null,"up":"67","down":"0","forward":"0","comment":"22","uid":"23123009","name":"安可曲","header":"http://wimg.spriteapp.cn/profile/large/2019/07/03/5d1c79d980744_mini.jpg","top_comments_content":null,"top_comments_voiceuri":null,"top_comments_uid":null,"top_comments_name":null,"top_comments_header":null,"passtime":"2019-09-23 02:58:01"},{"sid":"29786296","text":"当孩子找爸爸要生活费时爸爸的反应。。。","type":"video","thumbnail":"http://wimg.spriteapp.cn/picture/2019/0922/5d86e8f712b71_wpd.jpg","video":"http://wvideo.spriteapp.cn/video/2019/0922/5d86e8f712b71_wpd.mp4","images":null,"up":"67","down":"2","forward":"0","comment":"19","uid":"23131296","name":"水山金公","header":"http://wimg.spriteapp.cn/profile/large/2019/07/04/5d1d903a089c4_mini.jpg","top_comments_content":null,"top_comments_voiceuri":null,"top_comments_uid":null,"top_comments_name":null,"top_comments_header":null,"passtime":"2019-09-23 02:18:01"},{"sid":"29786650","text":"看看情商高的人如何化解危机，还能给周人的人带来欢乐。","type":"video","thumbnail":"http://wimg.spriteapp.cn/picture/2019/0922/6773de70-dcfe-11e9-8ea4-1866daeb0df1_wpd.jpg","video":"http://wvideo.spriteapp.cn/video/2019/0922/6773de70-dcfe-11e9-8ea4-1866daeb0df1_wpd.mp4","images":null,"up":"450","down":"10","forward":"16","comment":"17","uid":"17604620","name":"小蟠桃","header":"http://wimg.spriteapp.cn/profile/large/2018/11/12/5be93e5b8d489_mini.jpg","top_comments_content":null,"top_comments_voiceuri":null,"top_comments_uid":null,"top_comments_name":null,"top_comments_header":null,"passtime":"2019-09-23 01:38:04"}]
     */

    private int code;
    private String message;
    private List<JokeEntity> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<JokeEntity> getResult() {
        return result;
    }

    public void setResult(List<JokeEntity> result) {
        this.result = result;
    }
}


