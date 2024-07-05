<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home </a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bank"  >Daftar Dokreff</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Tambah Dokreff<span id='statusaddedit'></span></a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Ubah Dokreff</div>       
    </div>
    <div class="portlet-body flip-scroll">
         <form:form  method="post" commandName="spdBTLMaster"  id="spdBTLMasterform"  action='${pageContext.request.contextPath}/dokreff/updatedokreff' class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Pemprov/Daerah:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="idDokreff" id="idDokreff"/>
                        <form:input   path="namaDaerahJudul" id="namaDaerahJudul"    size="50" maxlength="100"      />  
                        <form:errors path="namaDaerahJudul" class="error" />
                    </div>
                </div>  
            </div>                      
            <div class="form-group">
                <label class="col-md-3 control-label">Daerah:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaDaerah" id="namaDaerah"    size="40" maxlength="50"   />  
                        <form:errors path="namaDaerah" class="error" />
                    </div>
                </div>  
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">Kota:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaKota" id="namaKota"    size="30" maxlength="30"   />  
                        <form:errors path="namaKota" class="error" />
                    </div>
                </div>  
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">No.Perda:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="noPerda" id="noPerda" size="3" maxlength="5" />  
                        <form:errors path="noPerda" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Perda:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="kodePerdaTanggal" id="kodePerdaTanggal"    
                                      class="required form-control form-control-inline input-small date-picker entrytanggal valid" size="14" />  
                        <form:errors path="kodePerdaTanggal" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="kodePerdaTahun" id="kodePerdaTahun"    size="4" maxlength="6"   />  
                        <form:errors path="kodePerdaTahun" class="error" />
                    </div>
                </div>  
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">Keterangan Perda SPD - 1:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="ketPeraturanSpd1" id="ketPeraturanSpd1"    size="60" maxlength="200"   />  
                        <form:errors path="ketPeraturanSpd1" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Keterangan Perda SPD - 2:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="ketPeraturanSpd2" id="ketPeraturanSpd2"    size="60" maxlength="200"   />  
                        <form:errors path="ketPeraturanSpd2" class="error" />
                    </div>
                </div>  
            </div>        
            <div class="form-group">
                <label class="col-md-3 control-label">Keterangan Perda SPD - 3:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="ketPeraturanSpd3" id="ketPeraturanSpd3"    size="60" maxlength="200"   />  
                        <form:errors path="ketPeraturanSpd3" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Keterangan Perda SPD - 4</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="ketPeraturanSpd4" id="ketPeraturanSpd4"    size="60" maxlength="200"   />  
                        <form:errors path="ketPeraturanSpd4" class="error" />
                    </div>
                </div>  
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">Keterangan Perda SPD - 5:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="ketPeraturanSpd5" id="ketPeraturanSpd5"    size="60" maxlength="200"   />  
                        <form:errors path="ketPeraturanSpd5" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama PPKD:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaPpkd" id="namaPpkd"    size="30" maxlength="30"   />  
                        <form:errors path="namaPpkd" class="error" />
                    </div>
                </div>  
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">NIP PPKD:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nipPpkd" id="nipPpkd"    size="18" maxlength="18"   />  
                        <form:errors path="nipPpkd" class="error" />
                    </div>
                </div>  
            </div>   
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" > Ubah </button>
                    <a class="btn blue"    href="${pageContext.request.contextPath}/dokreff" >Kembali</a>
                </div>
            </div>
        </form:form>
    </div>
</div> 