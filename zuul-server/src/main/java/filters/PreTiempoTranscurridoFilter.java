package filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PreTiempoTranscurridoFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);
    @Override
    public String filterType() { //antes que se resuelva la ruta y de la comunicacacion con el microservicio
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException { //logica del filtro

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s request enrutado a %s", request.getMethod(), request.getRequestURL().toString()));
        Long tiempoInicio =System.currentTimeMillis();
        request.setAttribute("tiempoinicio",tiempoInicio);
        return null;
    }
}
