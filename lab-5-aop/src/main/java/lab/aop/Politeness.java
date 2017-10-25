package lab.aop;


import lab.model.Customer;
import lab.model.CustomerBrokenException;
import lab.model.Squishee;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class Politeness {

    @Before("execution(* sellSquishee(..))")
    public void sayHello(JoinPoint joinPiont) {
        String name = ((Customer) joinPiont.getArgs()[0]).getName();
        AopLog.appendFormat("Hello %s. How are you doing? \n", name);
    }

    @AfterReturning(pointcut = "execution(* sellSquishee(..))",
            returning = "retVal", argNames = "retVal")
    public void askOpinion(Object retVal) {
        String name = ((Squishee) retVal).getName();
        AopLog.appendFormat("Is %s Good Enough?\n", name);
    }

    @AfterThrowing(pointcut = "execution(* sellSquishee(..))")
    public void sayYouAreNotAllowed() {
        AopLog.append("Hmmm... \n");
    }

//    @AfterThrowing(pointcut = "execution(* sellSquishee())", throwing = "e")
//    public void sayYouAreNotAllowed(CustomerBrokenException e) {
//        AopLog.append("Hmmm... \n");
//    }

    @After("execution(* sellSquishee(..))")
    public void sayGoodBye() {
        AopLog.append("Good Bye! \n");
    }

    @Around("execution(* sellSquishee(..))")
    public Object sayPoliteWordsAndSell(ProceedingJoinPoint pjp) throws Throwable {
        AopLog.append("Hi! \n");
        Object retVal = pjp.proceed();
        AopLog.append("See you! \n");
        return retVal;
    }
}