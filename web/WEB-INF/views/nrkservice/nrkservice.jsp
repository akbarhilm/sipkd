<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>    <li>

        <a href="${pageContext.request.contextPath}/beranda">Home </a> 
        <span class="icon-angle-right"></span>
    </li>
    
    <li><a href="#">Service NRK<span id='statusaddedit'></span></a></li>
</ul>
<div class="portlet box red">

    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Service NRK</div>       
    </div>
    <div class="portlet-body flip-scroll">

        <form:form  method="post" commandName="progcmd"  id="nrk"   action="${pageContext.request.contextPath}/" class="form-horizontal">         

            

            <div class="form-group">
                <label class="col-md-3 control-label">NRK :</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="text"  name="nrk"  id="nrk"  />
                    </div>
                </div>
            </div> 
                    
            <div class="form-group">
                <label class="col-md-3 control-label">NIP :</label>
                <div class="col-md-5">
                    <div class="input-group">
                       <input type="text"  name="nip"  id="nip"  />
                    </div>
                </div>
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">Nama :</label>
                <div class="col-md-5">
                    <div class="input-group">
                       <input type="text"  name="nama"  id="nama"  />
                    </div>
                </div>
            </div>
                   
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" id="btnSimpan" class="btn blue" onclick='getDataNrkGet()'>Cari</button>
                    
                </div>
            </div>
        </form:form>
    </div>
</div> 
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/nrkservice/nrkservice.js"></script>  