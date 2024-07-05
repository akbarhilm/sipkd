<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a> 
        <span class="icon-angle-right"></span>
    </li>   
    <li><a href="#">Daftar Berita Acara</a></li>
</ul>
 <form:form  method="get" commandName="progcmd"  id="spdBTLMasterform">
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Berita Acara</div>
        <div class="actions">

        </div>
    </div>
    <div class="portlet-body flip-scroll">
         
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Nama SKPD:</label>
                <div class="col-md-6">
                    <input type="hidden" id="idskpd" name="idskpd" value="${skpd.idSkpd}" onchange="gridsppbantuan()"  />
                    <input type="text"  name="skpd"  id="skpd"  class="m-wrap large" size="40"  value="${skpd.namaSkpd} / ${pengguna.nip}"  />
                    <input type="hidden" name="idkegiatan" id="idkegiatan" value="${idkegiatan}"  size="10"  class="m-wrap medium inputnumber" /> 
                    <input type="text" name="tahun" id="tahun" maxlength="4" size="10"  class="m-wrap medium inputnumber" value="${tahunAnggaran}" /> 
                </div>
            </div>
                     
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" class="btn dark" onclick='grid()'>Cari</button>
                </div>
            </div>

        </form>
                   
    </div>
</div>        


<div class="portlet box">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Daftar Berita Acara</div>

    </div>
    <div >
        <table id="kontrakpopup" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                <tr>
                    <th>No</th>
                    <th>Nama Akun</th>
                    <th>Pilih</th>
                    
                </tr>
            </thead>
            <tbody  >
            </tbody>
        </table> 
    </div>    
</div>
 </form:form>
        
        <script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/listpopupakun.js"></script>  
