package servlets.other.road_time;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import servlets.ShopServletService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SaveRoadTime"
            ,urlPatterns = "/save.road_time")

public class SaveRoadTime extends HttpServlet {

    private String c = "sav.rdt";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

        ShopServletService sSS = new ShopServletService(ctx);

        if (sSS.initializeShop(request, response)) {

            if (!sSS.getRequestJ().has("road_time")) {

                sSS.getStatus().put("error", "Отсутствует ключ")
                        .put("case", c);

                sSS.finalizeShop(response);
                return;
            }
                ShopServletService.roadTime = sSS.getRequestJ().getInt("road_time");

            if (ShopServletService.roadTime < 0) {

                sSS.getStatus().put("error", "Некорректное число road_time")
                        .put("case", c);

                sSS.finalizeShop(response);
                return;

            }

            }else {

            return;
        }

        sSS.getStatus().put("error", "ok")

                .put("case",c);

        sSS.finalizeShop(response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }
}
