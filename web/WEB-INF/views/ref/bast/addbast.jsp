<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>    <li>

        <a href="${pageContext.request.contextPath}/beranda">Home </a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bast"  >Daftar  Berita Acara Serah Terima (BAST)</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Tambah  Berita Acara Serah Terima<span id='statusaddedit'></span></a></li>
</ul>
<div class="portlet box red">

    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Tambah  Berita Acara Serah Terima (BAST)</div>       
    </div>
    <div class="portlet-body flip-scroll">

        <form:form  method="post" commandName="progcmd"  id="spdBTLMasterform"   action="${pageContext.request.contextPath}/bast/simpanbast" class="form-horizontal">         

            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="idBast" id='idBast'     />                  
                        <form:input path="tahunAnggaran" id="tahunAnggaran" cssClass="required" type="text" readonly="true" size="4" maxlength="6" value="${tahunAnggaran}"  /> 
                        <form:errors path="tahunAnggaran" class="tahunAnggaran" />
                    </div>
                </div>
            </div>


            <div class="form-group">
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="kontrak.idKontrak" id="idKontrak" value="${kontrak.idKontrak}"  cssClass="required" size="10" maxlength="180" readonly='true'  />

                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd' value="${skpd.idSkpd}"   />
                        <form:input type="text"  path="skpd.namaSkpd" name="skpd"  value="${skpd.kodeSkpd}/${skpd.namaSkpd}" id="skpd"  class="m-wrap large" size="55" readOnly="true" />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="skpd.idSkpd" class="label label-important" />
                    </div>
                </div>
            </div> 


            <div class="form-group">
                <label class="col-md-3 control-label">Kegiatan:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="idkegiatan" id='idkegiatan' readonly='true' value="${idkegiatan}"  />
                        <form:input path="kegiatan.namaKegiatan" id="namaKegiatan"  value="${kegiatan.namaKegiatan}" cssClass="required"  onchange="addakun()"  type="text" size="80" readonly='true'  />
                        &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/bast/listpopupkontrak/?target='_top'" title="Pilih Kegiatan"  ><i class="icon-zoom-in"></i> Pilih</a> 

                    </div>
                </div>
            </div>


            

            <div class="form-group">
                <label class="col-md-3 control-label">Nomor Berita:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="noBast" id="noBast"  readonly="true"  size="10" maxlength="50"  />  
                        <form:errors path="noBast" class="error" />
                    </div>
                </div>  
            </div>     
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Berita :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" name="tglBast" path="tglBast" id="tglBast" class="required form-control form-control-inline input-small date-picker entrytanggal2 valid" size="14"  />
                        <form:errors path="tglBast" class="error" />
                    </div>
                </div>  
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">Nama PPTK :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaPptk" id="namaPptk"    size="55" maxlength="50"   />  
                        <form:errors path="namaPptk" class="error" />
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">NIP PPTK :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nipPptk" id="nipPptk"    size="20" maxlength="18"   />  
                        <form:errors path="nipPptk" class="error" />
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">Keterangan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="ketBast" id="ketBast"    size="55" maxlength="250"   />  
                        <form:errors path="ketBast" class="error" />
                    </div>
                </div>  
            </div>   


            <div >
                <table id="basttable" class="table table-striped table-bordered table-condensed table-hover " >
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Akun</th>  
                            <th>Nilai Bast</th> 
                            <th>Nilai Prestasi</th>             
                        </tr>                        
                    </thead>
                    <tbody  >
                    </tbody>
                </table> 
            </div>      


            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" > Simpan </button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/bast" >Kembali</a>
                </div>
            </div>
        </form:form>
    </div>
</div> 
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/listpopupakun.js"></script>  