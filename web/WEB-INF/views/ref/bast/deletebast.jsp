<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>    <li>

        <a href="${pageContext.request.contextPath}/beranda">Home </a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bast"  >Daftar  Berita Acara Serah Terima</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Hapus Berita Acara Serah Terima<span id='statusaddedit'></span></a></li>
</ul>
<div class="portlet box red">

    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Hapus  Berita Acara Serah Terima</div>       
    </div>
    <div class="portlet-body flip-scroll">

        <form:form  method="post" commandName="spdBTLMaster"  id="spdBTLMasterform"   action="${pageContext.request.contextPath}/bast/deletebasts" class="form-horizontal">         

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
                        <form:input type="text"  path="skpd.namaSkpd" name="skpd"  value="${skpd.kodeSkpd}/ ${skpd.namaSkpd}" id="skpd"  class="m-wrap large" size="40" readOnly="true" />
                        
                        <form:errors path="skpd.idSkpd" class="label label-important" />
                    </div>
                </div>
            </div> 


            <div class="form-group">
                <label class="col-md-3 control-label">Kegiatan:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="idkegiatan" id='idkegiatan' readonly='true' value="${idkegiatan}" />
                        <form:input path="kegiatan.namaKegiatan" id="namaKegiatan"  value="${kegiatan.namaKegiatan}" cssClass="required"   type="text" size="55" readonly='true'  />
                       
                       
                    </div>
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-3 control-label">Akun :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden   path="akun.idAkun" id="idAkun"   size="10" maxlength="50" cssClass="required"   />  
                        <form:input   path="akun.namaAkun" id="namaAkun"   size="55" maxlength="50" cssClass="required" readonly='true'  />  
                        <form:errors path="idAkun" class="error" />
                       
                    </div>
                </div>  
            </div>   

            <div class="form-group">
                <label class="col-md-3 control-label">Nomor Berita:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="noBast" id="noBast"    size="10" maxlength="50"  cssClass="required" readonly='true' />  
                        <form:errors path="noBast" class="error" />
                    </div>
                </div>  
            </div>     
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Berita :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" name="tglBast" path="tglBast" id="tglBast" class="required form-control form-control-inline input-small date-picker entrytanggal valid" size="14" value="" readonly='true'/>
                        <form:errors path="tglBast" class="error" />
                    </div>
                </div>  
            </div>     

            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Prestasi :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nilaiPrestasi" id="nilaiPrestasi"    size="5" maxlength="3" readonly='true'  />  
                        <form:errors path="nilaiPrestasi" class="error" />
                    </div>
                </div>  
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai BAST :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nilaiBast" id="nilaiBast"    size="25" maxlength="20" class="inputinvoice" readonly='true'  />  
                        <form:errors path="nilaiBast" class="error" />
                    </div>
                </div>  
            </div>  

            <div class="form-group">
                <label class="col-md-3 control-label">Nama PPTK :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaPptk" id="namaPptk"    size="55" maxlength="50"  readonly='true' />  
                        <form:errors path="namaPptk" class="error" />
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">NIP PPTK :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nipPptk" id="nipPptk"   readonly="true" size="20" maxlength="18"   />  
                        <form:errors path="nipPptk" class="error" />
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">Keterangan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="ketBast" id="ketBast"    size="55" maxlength="50"  readonly='true' />  
                        <form:errors path="ketBast" class="error" />
                    </div>
                </div>  
            </div>   


            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" > Hapus </button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/bast" >Kembali</a>
                </div>
            </div>
        </form:form>
    </div>
</div> 
