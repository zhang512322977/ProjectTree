package cn.yueshutong.springprojecttree;

import cn.yueshutong.springprojecttree.config.annotation.EnableProjectTree;

@EnableProjectTree("execution(* com.zlwl.istudy..*(..))")
public class App {
}
