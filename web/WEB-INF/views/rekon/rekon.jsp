<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SP2D</a> 
        <span class="icon-angle-right"></span>
    </li>    
    <li><a href="#"> Rekon SP2D</a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Rekon SP2D</div>
        <!--  <div class="actions">
              <a href="#" onclick="pindahhalamanadd()" class="btn dark"><i class="icon-plus"></i> Tambah</a> 
          </div>-->
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">

            <div class="form-group">
                <label class="col-md-3 control-label">Tahun :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" readonly="true" name="tahun" id="tahun" maxlength="4" size="10" value="${tahunAnggaran}" /> 
                    </div>
                </div>
            </div>

            <div class="form-group" id="labeltanggal">
                <label class="col-md-3 control-label">Tanggal :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" id="tanggal" name="tanggal"  class="required date-picker entrytanggal2 valid" size="14" /> 
                    </div>
                </div>  
            </div> 

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button type="button" id="btncetak" class="btn blue" onclick='downloadrekon()' href="#" > Unduh File</button>
                        
                </div>
            </div> 

        </form>

    </div>

</div>    

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/rekon/rekon.js"></script>  
