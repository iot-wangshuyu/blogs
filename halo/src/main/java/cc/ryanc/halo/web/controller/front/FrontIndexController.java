package cc.ryanc.halo.web.controller.front;

import cc.ryanc.halo.model.domain.Post;
import cc.ryanc.halo.model.dto.HaloConst;
import cc.ryanc.halo.service.PostService;
import cc.ryanc.halo.web.controller.core.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author : RYAN0UP
 * @date : 2018/4/26
 */
@Slf4j
@Controller
@RequestMapping(value = {"/", "index"})
public class FrontIndexController extends BaseController {

    @Autowired
    private PostService postService;


    /**
     * 请求首页
     *
     * @param model model
     * @return 模板路径
     */
    @GetMapping
    public String index(Model model) {
        //调用方法渲染首页
        return this.index(model, 1);
    }

    /**
     * 首页分页
     *
     * @param model model
     * @param page  当前页码
     * @param size  每页数量
     * @return 模板路径/themes/{theme}/index
     */
    @GetMapping(value = "page/{page}")
    public String index(Model model,
                        @PathVariable(value = "page") Integer page) {
        Sort sort = new Sort(Sort.Direction.DESC, "postDate");
        //默认显示10条
        Integer size = 10;
        //尝试加载设置选项，用于设置显示条数
        if (!StringUtils.isBlank(HaloConst.OPTIONS.get("index_posts"))) {
            size = Integer.parseInt(HaloConst.OPTIONS.get("index_posts"));
        }
        //所有文章数据，分页
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Post> posts = postService.findPostByStatus(pageable);
        if (null == posts) {
            return this.renderNotFound();
        }
        model.addAttribute("posts", posts);
        return this.render("index");
    }

    /**
     * ajax分页
     *
     * @param page page 当前页码
     * @return List
     */
    @GetMapping(value = "next")
    @ResponseBody
    public List<Post> ajaxIndex(@PathParam(value = "page") Integer page) {
        Sort sort = new Sort(Sort.Direction.DESC, "postDate");
        //默认显示10条
        Integer size = 10;
        //尝试加载设置选项，用于设置显示条数
        if (!StringUtils.isBlank(HaloConst.OPTIONS.get("index_posts"))) {
            size = Integer.parseInt(HaloConst.OPTIONS.get("index_posts"));
        }

        //文章数据，只获取文章，没有分页
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        List<Post> posts = postService.findPostByStatus(pageable).getContent();
        return posts;
    }

    /**
     * 搜索文章
     *
     * @param keyword keyword
     * @param model   model
     * @return 模板路径/themes/{theme}/index
     */
    @GetMapping(value = "search")
    public String search(@PathParam("keyword") String keyword, Model model) {
        Page<Post> posts = postService.searchByKeywords(keyword, null);
        model.addAttribute("posts", posts);
        return this.render("index");
    }
}
