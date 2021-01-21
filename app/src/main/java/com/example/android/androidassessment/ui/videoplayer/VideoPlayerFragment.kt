package com.example.android.androidassessment.ui.videoplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.androidassessment.databinding.VideoPlayerFragmentBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.util.Util

class VideoPlayerFragment : Fragment() {

    private var _binding: VideoPlayerFragmentBinding? = null
    private var player: SimpleExoPlayer? = null

    private val binding get() = _binding!!

    private var videoUrl = ""
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = VideoPlayerFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subjectNavigation =
            VideoPlayerFragmentArgs.fromBundle(requireArguments()).subjectNavigation

        // Set video url
        videoUrl = subjectNavigation.lessonMediaUrl

        // Set other views
        binding.lessonName.text = subjectNavigation.lessonName
        binding.chapterName.text = subjectNavigation.chapterName

        binding.backImageView.setOnClickListener {
            this.findNavController().navigate(
                VideoPlayerFragmentDirections.actionVideoPlayerFragmentToSubjectFragment()
            )
        }

        initialisePlayer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
            initialisePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if (Util.SDK_INT < 24 || player == null) {
            initialisePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }

    private fun initialisePlayer() {
        if (player == null) {
            val trackSelector = DefaultTrackSelector(requireContext())
            trackSelector.setParameters(trackSelector.buildUponParameters().setMaxVideoSizeSd())
            player = SimpleExoPlayer.Builder(requireContext())
                .setTrackSelector(trackSelector)
                .build()
            binding.videoView.player = player
            val mediaItem: MediaItem = MediaItem.fromUri(videoUrl)
            player!!.setMediaItem(mediaItem)
            player!!.playWhenReady = playWhenReady
            player!!.seekTo(currentWindow, playbackPosition)
            player!!.prepare()
        }
    }

    private fun releasePlayer() {
        if (player != null) {
            playWhenReady = player!!.playWhenReady
            playbackPosition = player!!.currentPosition
            currentWindow = player!!.currentWindowIndex
            player!!.release()
            player = null
        }
    }
}