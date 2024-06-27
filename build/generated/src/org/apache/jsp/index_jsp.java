package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import data.Cart;
import java.util.List;
import data.Product;
import dbconn.DBConnection;
import DAO.ProductDAO;
import data.User;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/includes/header.jsp");
    _jspx_dependants.add("/includes/navbar.jsp");
    _jspx_dependants.add("/includes/footer.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("auth", auth);
    }
    ProductDAO pdao = new ProductDAO(DBConnection.getConnection());
    List<Product> products = pdao.getAllProducts();
    
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProducts = null;
    if (cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        ");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\">\n");
      out.write("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css\" />\n");
      out.write(" ");
      out.write("\n");
      out.write("        <title>Shopping Cart</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      out.write("    <nav class=\"navbar navbar-expand-lg navbar-light bg-light\">\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <a class=\"navbar-brand\" href=\"index.jsp\">E-Commerce Shopping Cart </a>\n");
      out.write("      <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n");
      out.write("        <span class=\"navbar-toggler-icon\"></span>\n");
      out.write("      </button>\n");
      out.write("\n");
      out.write("      <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n");
      out.write("        <ul class=\"navbar-nav ml-auto\">\n");
      out.write("          <li class=\"nav-item active\">\n");
      out.write("              <a class=\"nav-link\" href=\"index.jsp\">Home</a>\n");
      out.write("          </li>\n");
      out.write("          <li class=\"nav-item\">\n");
      out.write("              <a class=\"nav-link\" href=\"cart.jsp\">Cart <span class=\"badge badge-danger\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${cart_list.size()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</span> </a>\n");
      out.write("          </li>\n");
      out.write("          ");
 
              if (auth != null) { 
      out.write("\n");
      out.write("          <li class=\"nav-item\">\n");
      out.write("            <a class=\"nav-link\" href=\"orders.jsp\">Orders</a>\n");
      out.write("          </li>\n");
      out.write("          <li class=\"nav-item\">\n");
      out.write("            <a class=\"nav-link\" href=\"log-out\">Logout</a>\n");
      out.write("          </li>\n");
      out.write("          ");
 } else { 
      out.write("\n");
      out.write("           <li class=\"nav-item\">\n");
      out.write("               <a class=\"nav-link\" href=\"login.jsp\">Login</a>\n");
      out.write("          </li> ");
 } 
      out.write("\n");
      out.write("        </ul>\n");
      out.write("      </div>\n");
      out.write("      </div>\n");
      out.write("    </nav>");
      out.write("\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"card-header my-3\">All Products</div>\n");
      out.write("            <div class=\"row\">\n");
      out.write("                ");

                    if (!products.isEmpty()) {
                        for (Product p : products) { 
      out.write("\n");
      out.write("                <div class=\"col-md-3 my-3\">\n");
      out.write("                    <div class=\"card w-100\" style=\"width: 18rem;\">\n");
      out.write("                        <img class=\"card-img-top\" src= \"product_image/");
      out.print( p.getImage());
      out.write("\" alt=\"Card image cap\">\n");
      out.write("                        <div class=\"card-body\">\n");
      out.write("                            <h5 class=\"card-title\"> ");
      out.print( p.getName());
      out.write(" </h5>\n");
      out.write("                            <h6 class=\"price\">Price: $");
      out.print( p.getPrice() );
      out.write(" </h6>\n");
      out.write("                            <h6 class=\"category\">Category: ");
      out.print( p.getCategory());
      out.write(" </h6>\n");
      out.write("                            <div class=\"mt-3 d-flex justify-content-between\">\n");
      out.write("                                <a href=\"add-to-cart?id=");
      out.print(p.getId());
      out.write("\" class=\"btn btn-dark\">Add to Cart</a>\n");
      out.write("                                <a href=\"order-now?quantity=1&id=");
      out.print( p.getId() );
      out.write("\" class=\"btn btn-primary\">Buy Now</a>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                ");
 }
                    }

                
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        ");
      out.write("<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\"></script>\n");
      out.write("<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js\"></script>\n");
      out.write("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js\"></script>");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
