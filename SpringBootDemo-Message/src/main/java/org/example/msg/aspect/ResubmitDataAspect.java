//package org.example.msg.aspect;
//
//import com.alibaba.fastjson.JSONObject;
//import lombok.extern.log4j.Log4j;
//import org.apache.ibatis.binding.MapperMethod;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.example.annotation.Resubmit;
//import org.example.msg.lock.ResubmitLock;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//
///**
// * @author LR
// * @version 1.0
// * @date 2020/10/22 10:26
// * @Description
// */
//@Aspect
//@Component
//public class ResubmitDataAspect {
//
//    private final static String DATA = "data";
//    private final static Object PRESENT = new Object();
//
//    @Around("@annotation(org.example.annotation.Resubmit)")
//    public Object handleResubmit(ProceedingJoinPoint joinPoint) throws Throwable {
//        Method method = ((MapperMethod.MethodSignature) joinPoint.getSignature()).getMethod();
//        //获取注解信息
//        Resubmit annotation = method.getAnnotation(Resubmit.class);
//        int delaySeconds = annotation.delaySeconds();
//        Object[] pointArgs = joinPoint.getArgs();
//        String key = "";
//        //获取第一个参数
//        Object firstParam = pointArgs[0];
//        if (firstParam instanceof RequestDTO) {
//            //解析参数
//            JSONObject requestDTO = JSONObject.parseObject(firstParam.toString());
//            JSONObject data = JSONObject.parseObject(requestDTO.getString(DATA));
//            if (data != null) {
//                StringBuffer sb = new StringBuffer();
//                data.forEach((k, v) -> {
//                    sb.append(v);
//                });
//                //生成加密参数 使用了content_MD5的加密方式
//                key = ResubmitLock.handleKey(sb.toString());
//            }
//        }
//        //执行锁
//        boolean lock = false;
//        try {
//            //设置解锁key
//            lock = ResubmitLock.getInstance().lock(key, PRESENT);
//            if (lock) {
//                //放行
//                return joinPoint.proceed();
//            } else {
//                //响应重复提交异常
//                return new ResponseDTO<>(ResponseCode.REPEAT_SUBMIT_OPERATION_EXCEPTION);
//            }
//        } finally {
//            //设置解锁key和解锁时间
//            ResubmitLock.getInstance().unLock(lock, key, delaySeconds);
//        }
//    }
//}
