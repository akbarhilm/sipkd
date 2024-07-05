<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Setor</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="#" onclick="pindahhalamanadepan()">Daftar Setor</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Ubah  Setor</a></li>
</ul>
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<form:form  method="post" commandName="refsetor"  id="refsetor"   action="${pageContext.request.contextPath}/setor/simpansetor2biaya" class="form-horizontal">
    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Form Setor</div>       
        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd" value="${isadd}" />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                    <form:hidden path="tahunSetor" id="tahunSetor" value="${tahunAnggaran}" /> 
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd'   onChange="getpagudanspd(this.value)" value="${skpd.idSkpd}"  />
                        <form:input path="skpd" type="text"  name="skpd"  id="skpd" readonly="true" class="m-wrap large" size="90"  value="${skpd.kodeSkpd} / ${skpd.namaSkpd}  "  />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="skpd.idSkpd" class="label label-important" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">No Setor:</label>
                <div class="col-md-5">
                    <div class="input-group">                      
                        <form:input path="NoSetor" type="text"  name="NoSetor"  id="NoSetor" readonly="true" class="m-wrap large" size="20"    />
                    </div>
                </div>
            </div>
            <div class="form-group">

                <div class="col-md-5">
                    <div class="input-group">                      
                        <form:input type="hidden" path="idSetor" name="idSetor"  id="idSetor" class="m-wrap large" size="20"    />
                    </div>
                </div>
            </div>   

            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Setor :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" name="tglSetor" path="tglSetor" id="tglSetor" readonly="false" class="required form-control form-control-inline input-small date-picker2 entrytanggal valid" size="14" value=""/>
                        <form:errors path="tglSetor" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">BEBAN</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="beban" disabled="true">
                            <option value="LS" selected>LS</option>
                            <!--  <option value="TU">TU</option>
                              <option value="UP">UP/GU</option> -->
                        </form:select>
                        <form:errors path="beban" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Kode Jenis</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="jenis" disabled="true">
                            <!--  <option value="3" selected>BL</option>
                              <option value="1">BTL BANTUAN</option>
                              <option value="2">BTL</option>-->
                            <option value="4">BIAYA</option> 
                        </form:select>
                        <form:errors path="jenis" class="error" />
                    </div>
                </div>  
            </div>

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" > Ubah </button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/setor/indexbiaya" >Kembali</a>
                </div>
            </div>

        </div>
    </div>



    <div class="portlet">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Rincian Setor</div>       
        </div> 
        <div>
            <table id="forrinci" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th>Akun</th> 
                        <th>Nama Akun</th>
                        <th>Nilai Anggaran</th>
                        <th>Nilai Setoran</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody  >
                </tbody>
            </table> 
        </div> 
    </div> 
</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spjsetor/editsetorbiaya.js"></script>    
