<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a> 
        <span class="icon-angle-right"></span>
    </li>   
    <li><a href="#">Daftar SPD</a></li>
</ul>
 <form:form  method="get" commandName="refsppbantuan"  id="refsppbantuan">
      <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Daftar SPD</div>
        </div>
        <div class="portlet-body">
            <div class="form-horizontal" >
                
                <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="hidden" id="idskpdkordinator" value="${idskpdkordinator}" />
                         <form:hidden path="skpd.idSkpd" id='idskpdkoor' value="${skpd.idSkpd}" />
                        <input type="text" name="tahun" id="tahun" maxlength="4" size="10" value="${tahunAnggaran}"  class="m-wrap medium inputnumber" /> 
                    </div>
                </div>
            </div>
                <div class="form-actions fluid">
                    <div class="col-md-offset-3 col-md-9">
                        <button type="button" class="btn dark" onclick='grid()'>Cari</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="portlet box">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Daftar SPD</div>

        </div>
        <div >
            <table id="fungsitable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>No</th> 
                        <th>No. SPD</th>
                        <th>Kode Kegiatan / Nama Kegiatan</th>
                        <th>Kode Akun/ Nama Akun</th>
                        <th>Nilai SPD</th>
                        <th>Pilih</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table> 
        </div>    
    </div>
 </form:form>
        <script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/sppbantuan/listspdpopup.js"></script>  