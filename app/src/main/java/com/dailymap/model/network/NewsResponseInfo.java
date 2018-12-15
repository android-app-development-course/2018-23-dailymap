package com.dailymap.model.network;

import java.util.List;

/**
 * Created by WuchangI on 2018/11/17.
 */

public class NewsResponseInfo extends BaseResponseInfo
{

    /**
     * code : 1
     * msg : database connected succeed
     * newsContents : [{"title":"热门合资SUV推荐 这几款居家旅行两相宜","content":"['2019款奇骏2.5L车型还配备了4X4-i智能全模式\t\t四驱', '在主动安全科技上与2019款奇骏\t                                            2.5L CVT四驱至尊版一致', '配置方面的提升可以看做是2019款奇骏的核心升级方面', '得益于日产对                                             2019款奇骏的发动机进行全面提升', '2019款奇骏', '2019款奇骏2.5L车型更是搭载了BOSE全定制高                                              保真豪华音响系统', '2019款奇骏同样采用该家族式风格特点', '2019款奇骏焕新上市']","url":"http://auto.ifeng.com/beijing/daogou/2018/1115/338616.shtml","times":"2018-11-15","category":"军事","newFrom":"凤凰网"},{"title":"北京BJ20优惠1万元起 造型个性时尚年轻","content":"['BJ20车型保养/保险综合信息表 保养信息 质保周期 3年10万 保养周期 5000 更换机油机滤费用 500 \t\t         更换机油三滤费用 700 保险/贷款 首年全险费用 4000 首付费用 32000 以上信息仅供参考 具体费用\t\t                         以到店核算为准', '具体费用根据车型以到店核算为准', '具体费用根据车型不同以到店核算为准', '新车\t\t                         第一年保险费用在0.4万元左右', '2018款 北京BJ20 1.5T CVT尊贵型', '2018款 北京BJ20 1.5T CVT尊\t\t        \t         贵型', '首付3.2万元左右(包含车款、上牌、保险、购置税和担保金等),月供0.2万元左右', '保养费用：                                            BJ20车型享受3年10万公里整车质保']","url":"http://auto.ifeng.com/beijing/jiangjia/2018/1115/336884.shtml","times":"2018-11-15","category":"军事","newFrom":"凤凰网"}]
     */

    /**
     * 1:表示数据库连接成功 0：表示数据库连接失败
     */
    private String code;

    /**
     * 表示返回code对应的连接信息
     */
    private String msg;

    /**
     * 新闻列表
     */
    private List<NewsContentsBean> newsContents;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public List<NewsContentsBean> getNewsContents()
    {
        return newsContents;
    }

    public void setNewsContents(List<NewsContentsBean> newsContents)
    {
        this.newsContents = newsContents;
    }


    /**
     * 新闻列表
     */
    public static class NewsContentsBean
    {
        /**
         * title : 热门合资SUV推荐 这几款居家旅行两相宜
         * content : ['2019款奇骏2.5L车型还配备了4X4-i智能全模式		四驱', '在主动安全科技上与2019款奇骏                                           2.5L CVT四驱至尊版一致', '配置方面的提升可以看做是2019款奇骏的核心升级方面', '得益于日产对                                             2019款奇骏的发动机进行全面提升', '2019款奇骏', '2019款奇骏2.5L车型更是搭载了BOSE全定制高                                              保真豪华音响系统', '2019款奇骏同样采用该家族式风格特点', '2019款奇骏焕新上市']
         * url : http://auto.ifeng.com/beijing/daogou/2018/1115/338616.shtml
         * times : 2018-11-15
         * category : 军事
         * newFrom : 凤凰网
         */

        /**
         * 新闻标题
         */
        private String title;

        /**
         * 新闻内容
         */
        private String content;

        /**
         * 新闻原链接
         */
        private String url;

        /**
         * 新闻发布时间
         */
        private String times;

        /**
         * 新闻类别
         */
        private String category;

        /**
         * 新闻来源网站
         */
        private String newFrom;

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }

        public String getContent()
        {
            return content;
        }

        public void setContent(String content)
        {
            this.content = content;
        }

        public String getUrl()
        {
            return url;
        }

        public void setUrl(String url)
        {
            this.url = url;
        }

        public String getTimes()
        {
            return times;
        }

        public void setTimes(String times)
        {
            this.times = times;
        }

        public String getCategory()
        {
            return category;
        }

        public void setCategory(String category)
        {
            this.category = category;
        }

        public String getNewFrom()
        {
            return newFrom;
        }

        public void setNewFrom(String newFrom)
        {
            this.newFrom = newFrom;
        }
    }
}
