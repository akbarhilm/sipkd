$(document).ready(function() {
    cekBanyakImage();
    
});

function cekBanyakImage() {
    var banyak = $("#banyakImage").val();

    if (banyak > 0) {
        createSlideBlank();
        imagePopup();

    } else {
        document.getElementById('myImg').style.display = "none";
        cekBanyakBerita();
    }
}

function imagePopup() {
    var modal = document.getElementById('myModal');
    var img = document.getElementById('myImg');
    var modalImg = document.getElementById("img01");
    var captionText = document.getElementById("caption");
    var pathImage = $("#pathImage").val();

    img.src = "../BERITA/" + pathImage;

    modal.style.display = "block";
    modalImg.src = img.src;
    captionText.innerHTML = img.alt;


// Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

// When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
        img.style.display = "none";
        $("#divslide").html("");
        cekBanyakBerita();
    };

}

function cekBanyakBerita() {
    var banyak = $("#banyak").val();

    if (banyak > 0) {
        createSlide();
    } else {
        createSlideTidakAdaBerita();
    }
}

function createSlideBlank() {
    var divslide, slide, caption, sub, subsub;

    var img1 = "<img src='" + getbasepath() + "/static/assets_login/img/slide5.png' alt=''>";

    divslide = $("#divslide").html();
    slide = "<div class='item active' id='itemActive'></div>";
    $("#divslide").html(divslide + slide);

    caption = "<div class='carousel-caption col-md-11' id='captionblank'></div>";
    $("#itemActive").html(img1 + caption);

    sub = "<div class='portlet-body flip-scroll' id='sub'></div>";
    $("#captionblank").html(sub);

    // console.log("caption.html = "+$("#caption").html());

    subsub = "<div class='col-md-12' id='subsub'></div>";
    $("#sub").html(subsub);

}

function createSlideTidakAdaBerita() {
    var divslide, slide, caption, sub, subsub, divsubsub, labeltujuan;

    var img1 = "<img src='" + getbasepath() + "/static/assets_login/img/slide1.png' alt=''>";

    divslide = $("#divslide").html();
    slide = "<div class='item active' id='itemActive'></div>";
    $("#divslide").html(divslide + slide);

    caption = "<div class='carousel-caption col-md-11' id='captionblank'></div>";
    $("#itemActive").html(img1 + caption);

    sub = "<div class='portlet-body flip-scroll' id='sub'></div>";
    $("#captionblank").html(sub);

    // console.log("caption.html = "+$("#caption").html());

    subsub = "<div class='col-md-12' id='subsub'></div>";
    $("#sub").html(subsub);

    labeltujuan = "<label class='col-md-12' id='labelberitaawal'  align='left' style='color: #005580' ></label>";
    divsubsub = "<div class='form-group'><div class='col-md-1'><TEXTAREA id='isiberita' style='margin: 0px; width: 465px; height: 184px; opacity: 0.6' readonly='true'></TEXTAREA></div></div>";
    $("#subsub").html(labeltujuan + divsubsub);

    $("#isiberita").val("Surat Perintah Pencairan Dana yang selanjutnya disingkat SP2D adalah dokumen yang digunakan sebagai dasar pencairan dana yang diterbitkan oleh BUD berdasarkan SPM.");
    document.getElementById("isiberita").style.fontSize = "20px";
    //document.getElementById("isiberita").style.textAlign = "center";

}

function createSlide() {
    var banyak = $("#banyak").val();
    var divslide, slide, caption, sub, subsub, labeltujuan, divsubsub, labeljudulpdf, labelpdf; //labeldari, labeltgl

    var img1 = "<img src='" + getbasepath() + "/static/assets_login/img/slide1.png' alt=''>";
    var img2 = "<img src='" + getbasepath() + "/static/assets_login/img/slide2.png' alt=''>";
    var img3 = "<img src='" + getbasepath() + "/static/assets_login/img/slide3.png' alt=''>";
    var img4 = "<img src='" + getbasepath() + "/static/assets_login/img/slide4.png' alt=''>";
    var img5 = "<img src='" + getbasepath() + "/static/assets_login/img/slide5.png' alt=''>";


    for (var i = 1; i <= banyak; i++) {
        divslide = $("#divslide").html();
        if (i == 1) {
            slide = "<div class='item active' id='itemActive" + i + "'></div>";
        } else {
            slide = "<div class='item' id='itemActive" + i + "'></div>";
        }

        $("#divslide").html(divslide + slide);

        caption = "<div class='carousel-caption col-md-11' id='caption" + i + "'></div>";

        var lastChar = i.toString()[i.toString().length - 1];

        if (lastChar == "1" || lastChar == "6") {
            $("#itemActive" + i).html(img1 + caption);

        } else if (lastChar == "2" || lastChar == "7") {
            $("#itemActive" + i).html(img2 + caption);

        } else if (lastChar == "3" || lastChar == "8") {
            $("#itemActive" + i).html(img3 + caption);

        } else if (lastChar == "4" || lastChar == "9") {
            $("#itemActive" + i).html(img4 + caption);

        } else if (lastChar == "5" || lastChar == "0") {
            $("#itemActive" + i).html(img5 + caption);
        }

        sub = "<div class='portlet-body flip-scroll' id='sub" + i + "'></div>";
        $("#caption" + i).html(sub);

        subsub = "<div class='col-md-12' id='subsub" + i + "'></div>";
        $("#sub" + i).html(subsub);

        labeltujuan = "<label class='col-md-12' id='labelberitaawal" + i + "'  align='left' style='color: #005580' ></label>";
        divsubsub = "<div class='form-group'><div class='col-md-1'><TEXTAREA id='isiberita" + i + "' style='margin: 0px; width: 465px; height: 184px; opacity: 0.6' readonly='true'></TEXTAREA></div></div>";
        //labeldari = "<label class='col-md-12' id='labeldari" + i + "'  align='right' style='color: #005580' ></label>";
        //labeltgl = "<label class='col-md-12' id='labeltgl" + i + "'  align='right' style='color: #005580' ></label>";

        var pdf = document.getElementById('namaPdf').options[i - 1].innerHTML;
        if (pdf == "-") {
            labelpdf = "<label class='col-md-12' id='labelpdf" + i + "' align='left' ></label> ";
        } else {
            labelpdf = "<a class='col-md-12 link' id='labelpdf" + i + "' target='_new' align='left' onclick='downloadpdf(" + i + ")' ></a>  ";
        }

        $("#subsub" + i).html(labeltujuan + divsubsub + labelpdf);

    }

    setBerita();

}

function downloadpdf(i) {
    var namaberita = document.getElementById('namaPdf').options[i - 1].innerHTML;
    // document.getElementById('labelpdf'+i).href = getbasepath()+"/static/pdf_berita/permendagri-32-2017.pdf";
    document.getElementById('labelpdf' + i).href = "../BERITA/" + namaberita;
}


function setBerita() {
    var banyak = $("#banyak").val();
    var kepada, berita, pdf;
    var carousel, ol;

    for (var j = 1; j <= banyak; j++) {

    }

    for (var i = 1; i <= banyak; i++) {

        carousel = $("#idcarousel").html();
        var to = i - 1;

        if (i == 1) {
            ol = "<li data-target='#carousel-example-generic' data-slide-to='0' class='active'></li>";
            $("#idcarousel").html(ol);
        } else {
            ol = "<li data-target='#carousel-example-generic' data-slide-to='" + to + "' class=''></li>";
            $("#idcarousel").html(carousel + ol);
        }
        //console.log("carousel = "+carousel);



        kepada = document.getElementById('kepada').options[i - 1].innerHTML;
        //dari = document.getElementById('dari').options[i - 1].innerHTML;
        //tanggal = document.getElementById('tanggal').options[i - 1].innerHTML;
        berita = document.getElementById('berita').options[i - 1].innerHTML;
        pdf = document.getElementById('judulPdf').options[i - 1].innerHTML;

        //document.getElementById("labeldari" + i).innerHTML = "Dari : " + dari;
        //document.getElementById("labeltgl" + i).innerHTML = "Berlaku s.d " + tanggal;
        document.getElementById('labelberitaawal' + i).innerHTML = "Ditujukan : " + kepada;

        if (pdf == "-") {
            document.getElementById('labelpdf' + i).innerHTML = "";
        } else {
            document.getElementById('labelpdf' + i).innerHTML = pdf;
        }


        $("#isiberita" + i).val(berita);
        document.getElementById("isiberita" + i).style.fontWeight = 'bold';
        document.getElementById("labelberitaawal" + i).style.textShadow = "none";
        //document.getElementById("labeldari" + i).style.textShadow = "none";
        //document.getElementById("labeltgl" + i).style.textShadow = "none";

        //document.getElementById("labeldari"+i).style.fontSize = "12px";
        //document.getElementById("labeltgl"+i).style.fontSize = "12px";

    }


}