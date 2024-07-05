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

<form:form  method="post" commandName="refsetor"  id="refsetor"   action="${pageContext.request.contextPath}/setor_old/simpansetor2" class="form-horizontal">
    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Form Setor</div>       
        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Setor :</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd" value="${isadd}" />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                    <form:hidden path="tahunSetor" id="tahunSetor" value="${tahunAnggaran}" /> 
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD :</label>
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
                <label class="col-md-3 control-label">No Setor :</label>
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
                        <form:input type="text" name="tglSetor" path="tglSetor" id="tglSetor" readonly="false" class="required  date-picker2 entrytanggal2 valid" size="14" value="" />
                        <form:errors path="tglSetor" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Kode Beban :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="beban" >
                            <form:option value="LS">Langsung</form:option>
                        </form:select>

                        <form:errors path="beban" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Kode Jenis :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="jenis" disabled="true">
                            <option value="3" selected>Biaya Langsung (BL)</option>
                            <!--   <option value="1">BTL BANTUAN</option>
                               <option value="2">BTL</option>
                               <option value="4">BIAYA</option> -->
                        </form:select>
                        <form:errors path="jenis" class="error" />
                    </div>
                </div>  
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Temuan Pemeriksa :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:select path="temuan">
                            <form:option value="0">Bukan Temuan</form:option>
                            <form:option value="1">Temuan(SPJ Sudah Pengesahan)</form:option>
                            <form:option value="2">Temuan(SPJ Belum Pengesahan)</form:option>
                                <!--<option value="2">BTL</option>
                                <option value="4">BIAYA</option> -->
                        </form:select>
                        <form:errors path="temuan" class="error" />
                    </div>
                </div>  
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran :</label>
                <div class="col-md-4">
                    <form:input path="tahunAngg" id="tahunAngg" type="text" size="6" maxlength="4" readonly="true" /> 
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Kode Kegiatan :</label>
                <div class="col-md-4">
                    <input id="kegiatan" name="kegiatan" type="text" size="15" maxlength="11" readonly="true" placeholder="x.xx.xx.xxx"> 
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nama Kegiatan :</label>
                <div class="col-md-5">
                    <div class="input-group">                        
                        <input type="text"  name="namakegiatan"  id="namakegiatan" size="70" readonly="true">
                        <input type="hidden"  name="banyakrinci"  id="banyakrinci" readonly="true">
                        <input type="hidden"  name="idkegiatan"  id="idkegiatan" readonly="true" onchange='gridedit()'>
                    </div>
                </div>
            </div>
           
            <div class="form-group">
                <label class="col-md-3 control-label">Pagu Kegiatan :</label>
                <div class="col-md-5">
                    <div class="input-group">                        
                        <input type="text"  id="pagu" name="pagu"  readonly="true" >
                    </div>
                </div>
            </div>
                
            <div class="form-group">
                <label class="col-md-3 control-label">Keterangan :</label>
                <div class="col-md-5">
                    <div class="input-group">       
                        <form:textarea path="keterangan" id="keterangan" cols="50" rows="3" maxlength="150" readonly="false" /> 
                    </div>
                </div>
            </div>

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" > Simpan </button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/setor_old" >Kembali</a>
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
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spjsetor2016/editsetor.js"></script>    
